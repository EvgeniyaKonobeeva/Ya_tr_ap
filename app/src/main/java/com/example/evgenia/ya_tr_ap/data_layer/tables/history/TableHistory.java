package com.example.evgenia.ya_tr_ap.data_layer.tables.history;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;

import com.example.evgenia.ya_tr_ap.presentation_layer.MyApp;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by User on 24.04.2017.
 */

public class TableHistory implements ITableHistory{
    public static final String tableName = "TableHistory";
    public static final String TAG = "TableHistory";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SYNC_TIME, TEXT, TRANSLATE, LANG, DICTIONARY, HISTORY, FAVOR})
    public @interface Columns{}
    public static final String SYNC_TIME = "SYNC_TIME";
    public static final String LANG = "LANG";
    public static final String TEXT = "TEXT";
    public static final String TRANSLATE = "TRANSLATE";
    public static final String DICTIONARY = "DICTIONARY";
    public static final String HISTORY = "HISTORY";
    public static final String FAVOR = "FAVOR";
    SQLiteDatabase db;


    public Cursor getHistory(){
        db = MyApp.getDb();
        Cursor cursor = null;
        String query = "select " + TableHistory.SYNC_TIME + ", " +
                TableHistory.TEXT + "  ," +
                TableHistory.TRANSLATE + "  ," +
                TableHistory.DICTIONARY + "  ," +
                TableHistory.LANG + " , " +
                TableHistory.HISTORY + " , " +
                TableHistory.FAVOR +
                " from " + tableName +
                " where " + TableHistory.HISTORY + " = 1 " +
                "order by " + TableHistory.SYNC_TIME + " desc ;";
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getFavorites(){
        db = MyApp.getDb();
        Cursor cursor = null;
        String query = "select " + TableHistory.SYNC_TIME + ", " +
                TableHistory.TEXT + "  ," +
                TableHistory.TRANSLATE + "  ," +
                TableHistory.DICTIONARY + "  ," +
                TableHistory.LANG + " , " +
                TableHistory.HISTORY + " , " +
                TableHistory.FAVOR +
                " from " + tableName +
                " where " + TableHistory.FAVOR + " = 1 " +
                "order by " + TableHistory.SYNC_TIME + " desc ;";
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public boolean addToFavorites(HistoryFavorModel model){
        String query = "update " + tableName
                + " set " + SYNC_TIME + " = " + System.currentTimeMillis()
                +" , " + FAVOR + " = " + model.getFavorites()
                + " where " + TEXT + " like '" + model.getText() +"'"
                + " and " + LANG + " like '" + model.getLang()+"'"
                + " and " + TRANSLATE + " like '" + model.getTranslate() + "';" ;

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }


    public boolean addToHistory(HistoryFavorModel model){
        String query = "insert into " + tableName
                + " values (" +
                + System.currentTimeMillis() + " , "
                + model.getText().toLowerCase() + " , "
                + model.getTranslate().toLowerCase() + " , "
                + model.getDictionary().toLowerCase() + " , "
                + model.getLang().toLowerCase() + " , "
                + model.getHistory() + " , "
                + model.getFavorites() +
                " );";

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }

    public boolean addToHistoryPart(HistoryFavorModel model){
        String query = "insert into " + tableName
                + "( " + SYNC_TIME + ", " + TEXT + " , " + TRANSLATE + " , " + LANG + ", " + HISTORY + ", " + FAVOR +
                ")"
                + " values (" +
                + System.currentTimeMillis() + " , "
                + " '" + model.getText().toLowerCase() + "' , "
                +  " '" + model.getTranslate().toLowerCase() + "' , "
                +  " '" + model.getLang().toLowerCase() + "', " +
                + model.getHistory() + " , "
                + model.getFavorites() +
                " );";

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }

    public boolean deleteFromHistory (HistoryFavorModel model){
        String query = "update " + tableName
                + " set " + SYNC_TIME + " = " + System.currentTimeMillis()
                +" , " + HISTORY + " = " + model.getHistory()
                + " where " + TEXT + " like '" + model.getText() +"'"
                + " and " + LANG + " like '" + model.getLang()+"'"
                + " and " + TRANSLATE + " like '" + model.getTranslate() + "';" ;

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }

    public boolean clearHistory(){
        String query = "update " + tableName
                + " set " + SYNC_TIME + " = " + System.currentTimeMillis()
                +" , " + HISTORY + " = 0 ;";

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }

    public boolean clearFavorites(){
        String query = "update " + tableName
                + " set " + SYNC_TIME + " = " + System.currentTimeMillis()
                +" , " + FAVOR + " = 0 ;";

        db = MyApp.getDb();
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null) {
                cursor.moveToFirst();
                cursor.close();
                return true;
            }else return false;
        }else return false;
    }



}
