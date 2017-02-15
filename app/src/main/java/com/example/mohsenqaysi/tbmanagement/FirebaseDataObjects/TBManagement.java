package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * @Created by mohsenqaysi on 2/6/17.
 */

public class TBManagement extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* This is been called only ones
         * @return true to enable the offline data saving from FireBase
         */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true); // TODO: chnage it back to flase to hide the indicator
//        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);

        File httpCacheDirectory = new File(getCacheDir(), "picasso-cache");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().cache(cache);
        Picasso.Builder picassoBuilder = new Picasso.Builder(getApplicationContext());
        picassoBuilder.downloader(new OkHttpDownloader(this, Long.MAX_VALUE));
        Picasso picasso = picassoBuilder.build();
        try {
            Picasso.setSingletonInstance(picasso);
        } catch (IllegalStateException ignored) {
            Log.e("TAG; ", "Picasso instance already used");
        }
    }
}
