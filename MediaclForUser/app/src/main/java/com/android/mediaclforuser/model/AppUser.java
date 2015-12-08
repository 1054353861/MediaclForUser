package com.android.mediaclforuser.model;

/**
 * Created by Administrator on 2015/12/8.
 */
public class AppUser {
    private String id;//用户id
    private String user_name;//用户姓名
    private String phone;//用户手机号码
    private String check_code;//验证码
    private String new_phone;//新手机号码
    private String old_code;//旧手机的验证码
    private String new_code;//新手机验证码
    private int integral;//积分
    private String wx_openid;//微信登录码
    private String xl_openid;//新浪登录码
    private String imageUrl;//头像
    private int type;//短信发送接口，1 登录 2 注册  3 旧号码验证  4 新号码验证


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public String getNew_phone() {
        return new_phone;
    }

    public void setNew_phone(String new_phone) {
        this.new_phone = new_phone;
    }

    public String getOld_code() {
        return old_code;
    }

    public void setOld_code(String old_code) {
        this.old_code = old_code;
    }

    public String getNew_code() {
        return new_code;
    }

    public void setNew_code(String new_code) {
        this.new_code = new_code;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getWx_openid() {
        return wx_openid;
    }

    public void setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
    }

    public String getXl_openid() {
        return xl_openid;
    }

    public void setXl_openid(String xl_openid) {
        this.xl_openid = xl_openid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
