package com.android.mediaclforuser.ui;

import android.os.Bundle;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.ui.base.BaseActivity;
import com.android.mediaclforuser.ui.fragment.DiagnoseFragment;
import com.android.mediaclforuser.ui.fragment.SlidMenuRightFragment;

/**
 * Created by Administrator on 2015/12/11.
 */
public class HealthClassActivity extends BaseActivity {
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_helth_class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new DiagnoseFragment()).commit();

    }
}
