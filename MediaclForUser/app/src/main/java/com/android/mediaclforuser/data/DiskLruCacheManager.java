package com.android.mediaclforuser.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/12/25.
 */
public class DiskLruCacheManager {

    private final int size = 10 * 1024 * 1024;//缓存的大小
    private final int valueCount = 1;//同一个key对应多少的缓存文件
    public static final String MedicalForUser = "medical_for_user";

    private Gson gson;
    private DiskLruCache cache;

    public DiskLruCacheManager(Gson gson) {
        super();
        this.gson = gson;
    }

    /**
     * 如果我们要创建一个DiskLruCache的实例，则需要调用它的open()方法
     * 第一个参数指定的是数据的缓存地址
     * 第二个参数指定当前应用程序的版本号
     * 第三个参数指定同一个key可以对应多少个缓存文件，基本都是传1
     * 第四个参数指定最多可以缓存多少字节的数据
     * <p/>
     * uniqueName是缓存的数据类型   bitmap object
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public synchronized boolean open(Context context, String uniqueName) {
        try {
            File cacheDir = getDiskCacheDir(context, uniqueName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            cache = DiskLruCache.open(cacheDir, getAppVersion(context), valueCount, size);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断缓存中是否存在
     *
     * @param key
     * @return
     */
    public synchronized boolean exits(String key) {
        try {
            DiskLruCache.Snapshot snapshot = cache.get(key);
            if (snapshot != null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取出缓存数据
     * 取出的是Object对象
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public synchronized <T> T get(String key, Type type) {
        try {
            String data = cache.get(key).getString(0);
            T o = gson.fromJson(data, type);
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取出数据，非obj对象
     *
     * @param key
     * @param <T>
     * @return
     */
    public synchronized <T> T get(String key) {
        try {
            String data = cache.get(key).getString(0);
            T o = (T) data;
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储一个obj对象
     *
     * @param key
     * @param obj
     * @return
     */
    public synchronized boolean put(String key, Object obj) {
        String json = gson.toJson(obj);
        DiskLruCache.Editor editor = null;
        try {
            editor = cache.edit(key);
            editor.set(0, json);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 存储一个String对象
     *
     * @param key
     * @param str
     * @return
     */

    public synchronized boolean put(String key, String str) {
        DiskLruCache.Editor editor = null;
        try {
            editor = cache.edit(key);
            editor.set(0, str);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    public synchronized boolean remove(String key) {
        try {
            cache.remove(key);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 前缓存路径下所有缓存数据的总字节数，
     * 以byte为单位，如果应用程序中需要在界面上显示当前缓存数据的总大小，
     * 就可以通过调用这个方法计算出来
     *
     * @return
     */
    public synchronized long getSize() {
        if (!cache.isClosed())
            return cache.size();
        return 9999;
    }

    /**
     * 这个方法用于将DiskLruCache关闭掉，是和open()方法对应的一个方法。
     * 关闭掉了之后就不能再调用DiskLruCache中任何操作缓存数据的方法，
     * 通常只应该在Activity的onDestroy()方法中去调用close()方法。
     */
    public synchronized void close() {
        try {
            cache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法用于将所有的缓存数据全部删除，
     * 手动清理缓存功能，
     * 只需要调用一下DiskLruCache的delete()方法就可以实现了。
     */
    public synchronized void delete() {
        try {
            if (!cache.isClosed())
                cache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取缓存的地址，如果内存卡存在，调用getExternalCacheDir获取缓存位置
     * 获取到位置为：/sdcard/Android/data/<application package>/cache
     * 内存卡不存在，调用getCacheDir获取位置
     * 获取位置为：/data/data/<application package>/cache
     *
     * @param context
     * @param uniqueName
     * @return
     */
    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else
            cachePath = context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取手机当前的版本
     *
     * @param context
     * @return
     */
    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
