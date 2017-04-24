package com.example.evgenia.ya_tr_ap.data_layer.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.tables.history.TableHistory;
import com.example.evgenia.ya_tr_ap.data_layer.tables.languages.TableLanguages;


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
                    "_id Primary key, " +
                    TableLanguages.CODE + " TEXT ," +
                    TableLanguages.FULL_NAME + " TEXT ," +
                    TableLanguages.SYNC_TIME_TEXT + " BLOB ," +
                    TableLanguages.SYNC_TIME_TRANSLATE + " BLOB "
                    + " );";

    private String createHistoryTable =
            "CREATE TABLE IF NOT EXISTS " + TableHistory.tableName + "" +
                    "( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    TableHistory.SYNC_TIME + " BLOB ," +
                    TableHistory.TEXT + " TEXT ," +
                    TableHistory.TRANSLATE + " TEXT ," +
                    TableHistory.DICTIONARY + " TEXT ," +
                    TableHistory.LANG + " TEXT, " +
                    TableHistory.HISTORY + " INTEGER, " +
                    TableHistory.FAVOR + " INTEGER, " +
                    " UNIQUE ( " + TableHistory.TEXT +
                    ", " + TableHistory.LANG +
                    ", "+TableHistory.TRANSLATE + ") ON CONFLICT REPLACE "
                    + " );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");

        db.execSQL(createLangsTable);
        db.execSQL(createHistoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
