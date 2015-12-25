package com.android.mediaclforuser.data;


import com.android.mediaclforuser.model.AppUser;
import com.android.mediaclforuser.model.Data;
import com.android.mediaclforuser.model.Free;

import java.io.File;
import java.util.Date;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface GitHubService {
    @POST("/SickNoWorry/userapi/sendCheckCode")
    Observable<Data> getCode(@Query("phone") String phone);

    @POST("/SickNoWorry/userapi/login")
    Observable<Data<AppUser>> login(@Query("phone") String photo, @Query("check_code") String code);

    @FormUrlEncoded
    @POST("/SickNoWorry/userapi/register")
    Observable<Data<AppUser>> register(@Field("phone") String phone, @Field("user_name") String name, @Field("imageData") TypedFile file);

    @POST("/SickNoWorry/userapi/freelist")
    Observable<Data<Free>> getFree();

}
