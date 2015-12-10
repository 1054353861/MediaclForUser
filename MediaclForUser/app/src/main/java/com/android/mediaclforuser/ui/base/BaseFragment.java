package com.android.mediaclforuser.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mediaclforuser.data.CacheManger;
import com.android.mediaclforuser.utils.ACache;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/9.
 */
public abstract class BaseFragment extends Fragment{
    private final String TAG = "BaseFragment";

    protected  View view;
    protected ACache aCache;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(provideContentViewId(),container,false);
        ButterKnife.bind(this,view);
        aCache = ACache.get(inflater.getContext());
        Log.i(TAG, "---->onCreateView");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "---->onCreate");
    }

    protected abstract int provideContentViewId();
}
