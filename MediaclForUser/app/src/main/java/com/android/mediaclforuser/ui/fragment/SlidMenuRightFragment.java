package com.android.mediaclforuser.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.data.CacheManger;
import com.android.mediaclforuser.ui.HealthClassActivity;
import com.android.mediaclforuser.ui.base.BaseFragment;
import com.android.mediaclforuser.utils.ToastUtil;
import com.android.mediaclforuser.utils.Utils;
import com.android.mediaclforuser.view.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/9.
 */
public class SlidMenuRightFragment extends BaseFragment {
    private final String TAG = "SlidMenuRightFragment";

    @Bind(R.id.head_icon)
    CircleImageView mHeadIcon;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.phone)
    TextView mPhone;
    @Bind(R.id.integral)
    TextView mIntegral;


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_sliding_menu_right;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "---->onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "-------onResume");
        getUserInfo();
    }

    private void getUserInfo() {
        CacheManger cacheManger = new CacheManger();
        mName.setText(cacheManger.get(aCache, CacheManger.User_name));
        mIntegral.setText(cacheManger.get(aCache, CacheManger.INTEGRAL));
        mPhone.setText(Utils.changePhone(cacheManger.get(aCache, CacheManger.PHONE)));
        Picasso.with(getActivity()).load(cacheManger.get(aCache, CacheManger.INTEGRAL)).placeholder(R.drawable.touxiang).into(mHeadIcon);
    }

    @OnClick({R.id.user_info, R.id.family_info, R.id.health
            , R.id.doctor, R.id.health_class, R.id.diagnose
            , R.id.integral_exchange, R.id.setting})
    public void getClick(View view) {
        switch (view.getId()) {
            case R.id.user_info:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.family_info:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.health:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.doctor:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.health_class:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.diagnose:
               startActivity(new Intent().setClass(getActivity(), HealthClassActivity.class));
                break;
            case R.id.integral_exchange:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            case R.id.setting:
                ToastUtil.showToastShort(getActivity(), "用户信息");
                break;
            default:
                break;
        }

    }
}
