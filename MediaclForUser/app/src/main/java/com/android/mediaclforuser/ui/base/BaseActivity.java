package com.android.mediaclforuser.ui.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.mediaclforuser.data.CacheManger;
import com.android.mediaclforuser.data.DiskLruCacheManager;
import com.android.mediaclforuser.model.AppUser;
import com.android.mediaclforuser.utils.ACache;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/12/7.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected ACache aCache;
    protected DiskLruCacheManager cacheManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        aCache = ACache.get(this);
    }



    protected abstract int provideContentViewId();

}
