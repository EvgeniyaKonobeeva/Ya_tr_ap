package com.example.evgenia.ya_tr_ap.presentation_layer;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.BuildConfig;
import com.example.evgenia.ya_tr_ap.data_layer.database.SqliteHelper;
import com.example.evgenia.ya_tr_ap.presentation_layer.preferences.Preferences;

import java.util.Locale;

/**
 * Created by User on 18.04.2017.
 */

public class MyApp extends Application {
    private static final String TAG = "MyApp";
    private static SqliteHelper dbHelper;
//CBYUKNJY ,HFNM
    public static SQLiteDatabase getDb(){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getReadableDatabase();
            Log.d(TAG, "getDb: getting");
        }catch (SQLiteException slite) {
            Log.d(TAG, "getDb: " + slite.getMessage());
        }
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        dbHelper = new SqliteHelper(this);

        Preferences.createPreferences(this);

    }



    @Override
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory: ");
        dbHelper.close();
        dbHelper = null;
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate: ");
        dbHelper.close();
        dbHelper = null;
        super.onTerminate();
    }
}
