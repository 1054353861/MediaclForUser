package com.android.mediaclforuser.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.ui.base.BaseActivity;
import com.android.mediaclforuser.ui.fragment.InquiryForDoctor;
import com.android.mediaclforuser.ui.fragment.InquiryForHospital;
import com.android.mediaclforuser.ui.fragment.SlidMenuRightFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @Bind(R.id.left_btn)
    ImageButton mLeftBtn;
    @Bind(R.id.right_btn)
    ImageButton mRightBtn;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tab_host)
    FragmentTabHost mTabHost;

    private SlidingMenu menu;

    private Class mFragment[] = {InquiryForDoctor.class, InquiryForHospital.class};
    private int mImage[] = {R.drawable.main_activity_inquiry_for_doc_icon, R.drawable.main_activity_inquiry_for_hos_icon};
    private String mText[] = {"问诊", "寻医"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTabHost();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRightMenu();
    }

    @OnClick({R.id.left_btn,R.id.right_btn})
    public void  getOnClick(View v){
        switch (v.getId()){
            case R.id.left_btn:
                break;
            case R.id.right_btn:
                menu.showSecondaryMenu();
                break;
        }
    }

    private void initView() {
        mLeftBtn.setImageResource(R.drawable.btn_ad);
        mRightBtn.setImageResource(R.drawable.btn_menu);
        mLeftBtn.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(View.VISIBLE);
        mTitle.setText("看病无忧");
    }

    private void initRightMenu() {
        menu = new SlidingMenu(this);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.abc_action_bar_default_height_material);
        menu.setSecondaryMenu(R.layout.sliding_menu_right_contenter);
        menu.setFadeDegree(0.35f);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sliding_menu_right, new SlidMenuRightFragment()).commit();
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.real_tab_content);
        for (int i = 0; i < 2; i++) {
            //为每个Tab按钮设置图标，文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mText[i])
                    .setIndicator(getTabItemView(i));
            //将Tab按钮添加到Tab选项卡
            mTabHost.addTab(tabSpec, mFragment[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.fragment_tab_content_bg);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     *
     * @param index
     * @return
     */

    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.fragment_tab_content, null);
        ImageView mIcon = (ImageView) view.findViewById(R.id.icon);
        mIcon.setImageResource(mImage[index]);
        TextView mName = (TextView) view.findViewById(R.id.name);
        mName.setText(mText[index]);
        return view;
    }



}
