package com.android.mediaclforuser;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.mediaclforuser.data.GitHubService;
import com.android.mediaclforuser.data.RetroFitManager;
import com.android.mediaclforuser.utils.ACache;


/**
 * Created by Administrator on 2015/11/20.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        getInstance();
    }

    public static App getInstance(){
        if (instance==null){
            instance = new App();
        }
        return instance;
    }

    public GitHubService initGitHub(){
        return RetroFitManager.getInstance().setBsaeUrl();
    }

    public ACache initACache(){
        return ACache.get(getApplicationContext());
    }

    public  SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("ele_bus", Context.MODE_PRIVATE);
    }
}
