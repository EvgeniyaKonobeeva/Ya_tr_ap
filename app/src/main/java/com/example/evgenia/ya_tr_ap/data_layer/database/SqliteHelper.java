package com.example.evgenia.ya_tr_ap.data_layer.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.database.languages.TableLanguages;

/**
 * Created by User on 18.04.2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SqliteHelper";
    private static String DB_NAME = "databasefile";
    private static int dbVersion = 1;

    public SqliteHelper(Context context){
        super(context, DB_NAME, null, dbVersion);
    }

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private String createLangsTable =
            "CREATE TABLE IF NOT EXISTS " + TableLanguages.tableName + "" +
                    "( " +
                    TableLanguages.CODE + " TEXT ," +
                    TableLanguages.FULL_NAME + " TEXT ," +
                    TableLanguages.SYNC_TIME_TEXT + " LONG," +
                    TableLanguages.SYNC_TIME_TRANSLATE + " LONG "
                    + " );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");

        db.execSQL(createLangsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
