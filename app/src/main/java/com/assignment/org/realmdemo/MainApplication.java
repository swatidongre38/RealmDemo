package com.assignment.org.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("realmdemo.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
