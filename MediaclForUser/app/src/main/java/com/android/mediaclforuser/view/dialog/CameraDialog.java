package com.android.mediaclforuser.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.android.mediaclforuser.R;


/**
 * Created by xiangyi.wu on 2014/11/19.
 */
public class CameraDialog extends Dialog implements View.OnClickListener {
    Context context;
    private RelativeLayout mCameraRel,mPicRel;
    public PositiveButtonEvent mPositiveButtonEvent;
    public CancelButtonEvent mCancelButtonEvent;
    public CameraDialog(Context context) {
        super(context, R.style.main_agreement_dialog_style);
        this.context = context;
    }
    public CameraDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }
    protected CameraDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_camera);
        setWindow();
        initView();
    }
    private void initView() {
        mCameraRel = (RelativeLayout) findViewById(R.id.camera_rel);
        mCameraRel.setOnClickListener(this);
        mPicRel = (RelativeLayout) findViewById(R.id.pic_rel);
        mPicRel.setOnClickListener(this);
    }

    private void setWindow() {
        Window window = getWindow();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = (int) (d.getWidth() * 0.8);
        window.setAttributes(layoutParams);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.camera_rel) {
            if (mCancelButtonEvent != null) {
                mCancelButtonEvent.OnClick();
            }
            dismiss();
        }
        else{
            if (mPositiveButtonEvent!=null){
                mPositiveButtonEvent.OnClick();
            }
            dismiss();
        }
    }
    public CameraDialog setPositiveButton(PositiveButtonEvent positiveEvent) {
        if (positiveEvent == null) {
            return null;
        }
        this.mPositiveButtonEvent = positiveEvent;
        return this;
    }
    public CameraDialog setCannelButton(CancelButtonEvent cannelEvent) {
        if (cannelEvent == null) {
            return null;
        }
        this.mCancelButtonEvent = cannelEvent;
        return this;
    }
}
