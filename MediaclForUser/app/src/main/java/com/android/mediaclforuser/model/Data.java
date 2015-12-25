package com.android.mediaclforuser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/8.
 */
public class Data<T> {
    private int code;
    private String message;
    private T appuser;
    private String check_code;//验证码
    private List<T> frees = new ArrayList<T>() ;//义诊列表数据

    public List<T> getFrees() {
        return frees;
    }

    public void setFrees(List<T> frees) {
        this.frees = frees;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public T getAppuser() {
        return appuser;
    }

    public void setAppuser(T appuser) {
        this.appuser = appuser;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
