package com.svit.epolice;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initiate Twitter config
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("F6caWQmnHAgxEt86uZMCiA97B", "OsG0P4a8qZvYN5jIEKiEUVOPGZUnOCPPFCedZ8kDuuHpvMsKo1"))//pass Twitter API Key and Secret
                .debug(true)
                .build();
        Twitter.initialize(config);
    }
}
