package com.example.evgenia.ya_tr_ap.presentation_layer;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.database.SqliteHelper;

/**
 * Created by User on 18.04.2017.
 */

public class MyApp extends Application {
    private static final String TAG = "MyApp";
    private static SqliteHelper dbHelper;

    public static SQLiteDatabase getDb(){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getReadableDatabase();
        }catch (SQLiteException slite){
            Log.d(TAG, "getDb: " + slite.getMessage());
        }finally {
            dbHelper.close();
        }
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new SqliteHelper(this);
    }

    @Override
    public void onLowMemory() {
        dbHelper.close();
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        dbHelper.close();
        super.onTerminate();
    }
}
