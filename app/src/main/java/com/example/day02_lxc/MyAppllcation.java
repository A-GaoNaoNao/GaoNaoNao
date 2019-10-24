package com.example.day02_lxc;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.day02_lxc.Baen.DaoMaster;
import com.example.day02_lxc.Baen.DaoSession;

public class MyAppllcation extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = initPerson();
    }

    private DaoSession initPerson() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "person.db");
        SQLiteDatabase database = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
