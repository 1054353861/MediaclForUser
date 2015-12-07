package com.android.mediaclforuser.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/7.
 */
public abstract class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
    }

    protected abstract int provideContentViewId();

}
