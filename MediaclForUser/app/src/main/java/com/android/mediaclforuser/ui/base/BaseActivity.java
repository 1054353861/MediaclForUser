package com.android.mediaclforuser.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.mediaclforuser.utils.ACache;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/7.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        aCache = ACache.get(this);
    }

    protected abstract int provideContentViewId();

}
