package com.android.mediaclforuser.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.ui.base.BaseFragment;

/**
 * Created by Administrator on 2015/12/9.
 */
public class InquiryForHospital extends BaseFragment {
    private  final String TAG ="InquiryForHospital";

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_inquiry_for_hospital;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "------>onCreate");
    }












}
