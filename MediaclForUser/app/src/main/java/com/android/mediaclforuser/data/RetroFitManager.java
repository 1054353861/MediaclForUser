package com.android.mediaclforuser.data;


import retrofit.RestAdapter;

/**
 * Created by Administrator on 2015/11/30.
 */
public class RetroFitManager {

    private static RetroFitManager instance;

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
                .setEndpoint("http://tc.andone-media.com:8080/")
                .build();
    }

    public GitHubService setBsaeUrl() {
        return initRestAdapter().create(GitHubService.class);
    }

}
