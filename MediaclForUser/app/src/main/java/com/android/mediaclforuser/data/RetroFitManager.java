package com.android.mediaclforuser.data;


import retrofit.RestAdapter;
import retrofit.mime.TypedFile;

/**
 * Created by Administrator on 2015/11/30.
 */
public class RetroFitManager {

    private static RetroFitManager instance;
    public static final String URL = "http://139.196.49.238/";
    public static final String image = "http://139.196.49.238/res/";

    public static RetroFitManager getInstance() {
        if (instance == null) {
            instance = new RetroFitManager();
        }
        return instance;
    }


    private RetroFitManager() {
        super();
    }

    private RestAdapter initRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(URL)
                .build();
    }

    public GitHubService setBsaeUrl() {
        return initRestAdapter().create(GitHubService.class);
    }

}
