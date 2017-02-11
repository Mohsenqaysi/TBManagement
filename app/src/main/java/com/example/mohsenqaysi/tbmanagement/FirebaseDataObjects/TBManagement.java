package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

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
    }
}