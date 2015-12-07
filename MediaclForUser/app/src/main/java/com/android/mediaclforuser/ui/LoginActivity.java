package com.android.mediaclforuser.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mediaclforuser.R;
import com.android.mediaclforuser.ui.base.BaseActivity;
import com.android.mediaclforuser.utils.ToastUtil;
import com.android.mediaclforuser.view.CountDownAnimation;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/7.
 */
public class LoginActivity extends BaseActivity implements CountDownAnimation.CountDownListener {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.left_btn)
    ImageButton mLeftBtn;
    @Bind(R.id.right_btn)
    ImageButton mRightBtn;
    @Bind(R.id.get_code)
    TextView mGetCode;
    @Bind(R.id.agree_iv)
    ImageView mAgreeIv;
    @Bind(R.id.get_code_btn)
    private RelativeLayout mGetCodeBtn;

    private Boolean isClick = true;
    private CountDownAnimation countDownAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    private void initView(){
        mTitle.setText("登录");
        mLeftBtn.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(View.VISIBLE);
        countDownAnimation = new CountDownAnimation(mGetCode, 60);
        countDownAnimation.setCountDownListener(this);
    }

    @OnClick({R.id.left_btn,R.id.right_btn,R.id.get_code_btn,R.id.wei_xin_btn,R.id.wei_bo_btn})
    public void getClick(View view){
        switch (view.getId()){
            case R.id.left_btn://返回按钮
                break;
            case R.id.right_btn://完成按钮
                break;
            case R.id.get_code_btn://获取验证码
                getCode();
                break;
            case R.id.wei_xin_btn://微信登录
                break;
            case R.id.wei_bo_btn://微博登录
                break;
        }

    }

    private void getCode(){
        if (isClick) {
            isClick = false;
            countDownAnimation.start();
            mGetCode.setEnabled(false);
            mGetCodeBtn.setBackgroundResource(R.drawable.login_get_code_btn_unclick_bg);
        }
    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {
        isClick = true;
        mGetCode.setVisibility(View.VISIBLE);
        mGetCode.setText("获取验证码");
        mGetCode.setEnabled(true);
        mGetCodeBtn.setBackgroundResource(R.drawable.login_get_code_btn_bg);
    }
}
