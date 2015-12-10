package com.android.mediaclforuser.data;

import com.android.mediaclforuser.utils.ACache;

/**
 * Created by Administrator on 2015/12/3.
 */
public class CacheManger<T> {
    public static final String USER="User";
    public static final String User_name="user_name";
    public static final String INTEGRAL = "integral";
    public static final String PHONE = "phone";
    public static final String IMAGE_URL="image_url";
    public static final String ID = "id";


    public void save(ACache aCache,String str,T t){
        aCache.put(str,t);
    }

    public String get(ACache aCache,String str){
        return aCache.getAsString(str);
    }

    public void clear(ACache aCache){
        aCache.clear();
    }
    public void clear(ACache aCache,String str){
        aCache.remove(str);
    }

}
