package com.android.mediaclforuser.data;
import android.content.Context;

import com.android.elebus.App;
import com.android.elebus.model.User;
import com.android.elebus.model.UserData;
import com.android.elebus.provider.GetCodeEvent;
import com.android.elebus.provider.LoginEvent;
import com.android.elebus.ui.LoginActivity;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Administrator on 2015/12/3.
 */
public class LoginActivityRequestData {
    public  void getCode(Context context, String phone){
        final GetCodeEvent event = new GetCodeEvent();
        AppObservable.bindActivity((LoginActivity) context, App.getInstance().initGitHub().getCode(phone, "1"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        event.setCode(9999);
                        event.setMsg("网络连接失败!");
                        EventBus.getDefault().post(event);
                    }

                    @Override
                    public void onNext(UserData user) {
                        EventBus.getDefault().post(event);
                    }
                });
    }

    public void getLogin(Context mContext, String phone, String code){
        final LoginEvent<User> event = new LoginEvent();
        AppObservable.bindActivity((LoginActivity) mContext, App.getInstance().initGitHub().getUser(phone,code))
                .map(new Func1<UserData, User>()   {

                    @Override
                    public User call(UserData userData) {
                        User u = (User)userData.getT();
                        event.setCode(userData.getCode());
                        event.setMsg(userData.getMessage());
                        return u;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                event.setCode(9999);
                event.setMsg("网络连接失败！");
                EventBus.getDefault().post(event);
            }

            @Override
            public void onNext(User user) {
                App.getInstance().initACache().put(CacheManger.USER, user);
                EventBus.getDefault().post(event);
            }
        })
        ;
    }

}
