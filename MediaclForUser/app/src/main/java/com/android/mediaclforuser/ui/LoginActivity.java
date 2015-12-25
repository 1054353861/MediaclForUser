package com.android.mediaclforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mediaclforuser.App;
import com.android.mediaclforuser.R;
import com.android.mediaclforuser.data.CacheManger;
import com.android.mediaclforuser.data.RetroFitManager;
import com.android.mediaclforuser.model.AppUser;
import com.android.mediaclforuser.model.Data;
import com.android.mediaclforuser.ui.base.BaseActivity;
import com.android.mediaclforuser.utils.ACache;
import com.android.mediaclforuser.utils.ToastUtil;
import com.android.mediaclforuser.utils.Utils;
import com.android.mediaclforuser.view.CountDownAnimation;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

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
    RelativeLayout mGetCodeBtn;
    @Bind(R.id.phone_ed)
    EditText mPhoneEd;
    @Bind(R.id.code_ed)
    EditText mCodeEd;

    private Boolean isClick = true;
    private CountDownAnimation countDownAnimation;
    private String phone, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    private void initView() {
        mTitle.setText("登录");
        mLeftBtn.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(View.VISIBLE);
        countDownAnimation = new CountDownAnimation(mGetCode, 60);
        countDownAnimation.setCountDownListener(this);
    }

    @OnClick({R.id.left_btn, R.id.right_btn, R.id.get_code_btn, R.id.wei_xin_btn, R.id.wei_bo_btn})
    public void getClick(View view) {
        switch (view.getId()) {
            case R.id.left_btn://返回按钮
                finish();
                break;
            case R.id.right_btn://完成按钮
                getLoginInfo();
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

    private void getCode() {
        if (isClick) {
            isClick = false;
            countDownAnimation.start();
            mGetCode.setEnabled(false);
            mGetCodeBtn.setBackgroundResource(R.drawable.login_get_code_btn_unclick_bg);
            phone = mPhoneEd.getText().toString().trim();
            if (!Utils.isMobileNO(phone)) {
                ToastUtil.showToastShort(LoginActivity.this, "请输入正确手机号！");
                return;
            }
            getCodeData();
        }
    }

    private void getLoginInfo() {
        code = mCodeEd.getText().toString().trim();
        phone = mPhoneEd.getText().toString().trim();
        if (!Utils.isMobileNO(phone)) {
            ToastUtil.showToastShort(this, "请输入正确手机号！");
            return;
        }
        if (code.length() == 0) {
            ToastUtil.showToastShort(LoginActivity.this, "请输入验证码！");
            return;
        }
        login();
    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {
        isClick = true;
        mGetCode.setVisibility(View.VISIBLE);
        mGetCode.setText("获取验证码");
        mGetCode.setEnabled(true);
        mGetCodeBtn.setBackgroundResource(R.drawable.login_get_code_btn_bg);
    }

    /**
     * 获取验证码
     */
    private void getCodeData() {
        AppObservable.bindActivity(this, App.getInstance().initGitHub().getCode(phone))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToastShort(LoginActivity.this, e.getMessage());
                    }

                    @Override
                    public void onNext(Data data) {
                        //这里放置验证码
                        mCodeEd.setText(data.getCheck_code());
                        ToastUtil.showToastShort(LoginActivity.this, data.getMessage());
                    }
                });
    }

    /**
     * 用户登录
     */
    private void login() {
        AppObservable.bindActivity(this, App.getInstance().initGitHub().login(phone, code))
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Data<AppUser>, Integer>() {
                    @Override
                    public Integer call(Data<AppUser> appUserData) {
                        if (appUserData.getAppuser() != null) {
                            cache(appUserData.getAppuser());
                        }
                        ToastUtil.showToastShort(LoginActivity.this, appUserData.getMessage());
                        return appUserData.getCode();
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == 0) {
                    finish();
                } else if (integer != 1) {
                    startActivity(new Intent().setClass(LoginActivity.this, UserBasicInformationActivity.class));
                }
            }
        });
    }


    public void cache(AppUser user){
        CacheManger cacheManger = new CacheManger<String>();
        cacheManger.save(aCache, CacheManger.ID, user.getId());
        cacheManger.save(aCache,CacheManger.User_name,user.getUser_name());
        cacheManger.save(aCache,CacheManger.INTEGRAL,String.valueOf(user.getIntegral()));
        cacheManger.save(aCache,CacheManger.IMAGE_URL, RetroFitManager.image+user.getImageUrl());
        cacheManger.save(aCache, CacheManger.PHONE, user.getPhone());
    }




}
