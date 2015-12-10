package com.android.mediaclforuser.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.ui.base.BaseFragment;

/**
 * Created by Administrator on 2015/12/9.
 */
public class InquiryForDoctor extends BaseFragment {
    private  final String TAG ="InquiryForDoctor";
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_inquiry_for_doctor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"------>onCreate");
    }
}
