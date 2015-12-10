package com.android.mediaclforuser.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mediaclforuser.App;
import com.android.mediaclforuser.R;
import com.android.mediaclforuser.data.CacheManger;
import com.android.mediaclforuser.data.RetroFitManager;
import com.android.mediaclforuser.model.AppUser;
import com.android.mediaclforuser.model.Data;
import com.android.mediaclforuser.ui.base.BaseActivity;
import com.android.mediaclforuser.utils.ToastUtil;
import com.android.mediaclforuser.utils.Utils;
import com.android.mediaclforuser.view.CircleImageView;
import com.android.mediaclforuser.view.dialog.CameraDialog;
import com.android.mediaclforuser.view.dialog.CancelButtonEvent;
import com.android.mediaclforuser.view.dialog.PositiveButtonEvent;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.mime.TypedFile;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2015/12/8.
 */
public class UserBasicInformationActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.left_btn)
    ImageButton mLeftBtn;
    @Bind(R.id.right_btn)
    ImageButton mRightBtn;
    @Bind(R.id.head_icon)
    CircleImageView mHeadIcon;
    @Bind(R.id.name_ed)
    EditText mNameEd;
    @Bind(R.id.phone_ed)
    EditText mPhoneEd;
    @Bind(R.id.is_phone_iv)
    ImageView mIsPhoneIv;

    private String name, phone;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory().toString(), Utils.getPhotoFileName());
    private String path = "";
    File imgFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_user_basic_information;
    }

    private void initView() {
        mTitle.setText("基础信息");
        mLeftBtn.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(View.VISIBLE);
        mPhoneEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Utils.isMobileNO(s.toString())) {
                    phone = s.toString();
                    mIsPhoneIv.setImageResource(R.drawable.login_select_yes);
                } else {
                    phone = null;
                    mIsPhoneIv.setImageResource(R.drawable.login_select_no);
                }
            }
        });
    }

    @OnClick({R.id.left_btn, R.id.right_btn, R.id.head_icon})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.right_btn:
                if (imgFile == null) {
                    ToastUtil.showToastShort(this, "请选择上传头像！");
                    return;
                }
                name = mNameEd.getText().toString().trim();
                if (name == null) {
                    ToastUtil.showToastShort(this, "姓名不能为空！");
                    return;
                }
                if (phone == null) {
                    ToastUtil.showToastShort(this, "请输入正确手机号！");
                    return;
                }
                register();
                break;
            case R.id.head_icon:
                getHeadIcon();
                break;
        }
    }

    private void getHeadIcon() {
        new CameraDialog(this)
                .setCannelButton(new CancelButtonEvent() {
                    @Override
                    public void OnClick() {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 指定调用相机拍照后照片的储存路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    }
                }).setPositiveButton(new PositiveButtonEvent() {
            @Override
            public void OnClick() {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(tempFile), 450);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 450);
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null)
                    setPicToView(data);
                break;
        }
    }

    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //将进行剪裁后的图片显示到UI界面上
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            path = Utils.saveFile(photo, this);
            imgFile = new File(path);
            mHeadIcon.setImageBitmap(photo);

        }
    }


    private void register() {
        TypedFile typedFile = new TypedFile("image/jpeg", imgFile);
        AppObservable.bindActivity(this, App.getInstance().initGitHub().register(phone, name, typedFile))
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Data<AppUser>, Integer>() {

                    @Override
                    public Integer call(Data<AppUser> appUserData) {
                        if (appUserData.getAppuser() != null) {
                            cache(appUserData.getAppuser());
                        }
                        ToastUtil.showToastShort(UserBasicInformationActivity.this, appUserData.getMessage());
                        return appUserData.getCode();
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == 0) {

                } else {

                }
            }
        });
    }

    public void cache(AppUser user) {
        CacheManger cacheManger = new CacheManger<String>();
        cacheManger.save(aCache, CacheManger.ID, user.getId());
        cacheManger.save(aCache, CacheManger.User_name, user.getUser_name());
        cacheManger.save(aCache, CacheManger.INTEGRAL, String.valueOf(user.getIntegral()));
        cacheManger.save(aCache, CacheManger.IMAGE_URL, RetroFitManager.image + user.getImageUrl());
        cacheManger.save(aCache, CacheManger.PHONE, user.getPhone());
    }


}
