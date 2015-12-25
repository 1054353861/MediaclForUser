package com.android.mediaclforuser.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mediaclforuser.App;
import com.android.mediaclforuser.R;
import com.android.mediaclforuser.model.Data;
import com.android.mediaclforuser.model.Free;
import com.android.mediaclforuser.ui.LoginActivity;
import com.android.mediaclforuser.ui.adapter.ClickListener;
import com.android.mediaclforuser.ui.adapter.DiagnoseFreeFragmentAdapter;
import com.android.mediaclforuser.ui.base.BaseFragment;
import com.android.mediaclforuser.view.ContentLoaderView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Administrator on 2015/12/11.
 */
public class DiagnoseFragment extends BaseFragment implements ClickListener<Free>, ContentLoaderView.OnRefreshListener {

    @Bind(R.id.content_loader)
    ContentLoaderView loaderView;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;

    private Context mContext;
    private DiagnoseFreeFragmentAdapter adapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_health;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_health,container,false);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mContext = inflater.getContext();
        loaderView.setAdapter(adapter);
        loaderView.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DiagnoseFreeFragmentAdapter(getActivity());
        adapter.setClickListener(this);
        getData();
    }

    @Override
    public void OnClick(Free free) {
        startActivity(new Intent().setClass(mContext, LoginActivity.class));
    }

    @Override
    public void onRefresh(boolean fromSwipe) {
        getData();
    }

    private void getData() {
        AppObservable.bindActivity(getActivity(), App.getInstance().initGitHub().getFree())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Data<Free>, List<Free>>() {
                    @Override
                    public List<Free> call(Data<Free> freeData) {

                        return freeData.getFrees();
                    }
                }).subscribe(new rx.Observer<List<Free>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loaderView.notifyLoadFailed(e);
            }

            @Override
            public void onNext(List<Free> frees) {
                adapter.setListData(frees);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
