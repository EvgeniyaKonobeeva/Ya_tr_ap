package com.example.evgenia.ya_tr_ap.presentation_layer;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.evgenia.ya_tr_ap.data_layer.database.SqliteHelper;

/**
 * Created by User on 18.04.2017.
 */

public class MyApp extends Application {
    private static SqliteHelper dbHelper;

    public static SQLiteDatabase getDb(){
        if(dbHelper != null) {
            return dbHelper.getReadableDatabase();
        }else {
            return null;
        }
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
