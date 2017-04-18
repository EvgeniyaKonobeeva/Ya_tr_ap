package com.example.evgenia.ya_tr_ap.data_layer.database.languages;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.example.evgenia.ya_tr_ap.presentation_layer.MyApp;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 18.04.2017.
 */

public class TableLanguages implements ITableLanguages{
    public static final String tableName = "TableLanguages";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SYNC_TIME_TEXT, SYNC_TIME_TRANSLATE, CODE, FULL_NAME})
    public @interface Columns{}
    public static final String SYNC_TIME_TEXT = "SYNC_TIME_TEXT";
    public static final String SYNC_TIME_TRANSLATE = "SYNC_TIME_TRANSLATE";
    public static final String CODE = "CODE";
    public static final String FULL_NAME = "FULL_NAME";
    SQLiteDatabase db;


    public Cursor getAllTextLangs(){
        db = MyApp.getDb();
        String query = "select " + FULL_NAME + ", " + CODE
                + " from " + tableName
                + " where " + SYNC_TIME_TEXT + " > 0 "
                + " order by " + SYNC_TIME_TEXT + " desc limit " + Utils.NUMBER_RECENT_LANGS
                + " union all "
                + " select " + FULL_NAME
                + " from " + tableName
                + " order by " + FULL_NAME + ";" ;
        if(db != null) {
            return db.rawQuery(query, null);
        }else return  null;
    }

    public Cursor getAllTranslateLangs(){
        db = MyApp.getDb();
        String query = "select " + FULL_NAME + ", " + CODE
                + " from " + tableName
                + " order by " + SYNC_TIME_TRANSLATE + " desc limit " + Utils.NUMBER_RECENT_LANGS
                + " union all "
                + " select " + FULL_NAME
                + " from " + tableName
                + " order by " + FULL_NAME + ";" ;
        if(db != null) {
            return db.rawQuery(query, null);
        }else return  null;
    }


    public void updateSelectedTextLangs(String selected){
        db = MyApp.getDb();
        selected = selected.toLowerCase();
        String query = " update " + tableName
                + " set " + SYNC_TIME_TEXT + " = " + System.currentTimeMillis()
                + " where " + FULL_NAME + " like '" + selected + "' ;";
        if(db != null) {
            db.rawQuery(query, null);
        }
    }

    public void updateSelectedTranslateLangs(String selected){
        selected = selected.toLowerCase();
        String query = " update " + tableName
                + " set " + SYNC_TIME_TRANSLATE + " = " + System.currentTimeMillis()
                + " where " + FULL_NAME + " like '" + selected + "' ;";
    }


    /**
     * вставляем данные в таблицу
     * @param map ключ - код языка, значение - полное наименование */
    public void insertAllLangs(HashMap<String, String> map){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = MyApp.getDb();
        if(db != null){
            db.beginTransaction();
            for (Map.Entry<String, String> entry : map.entrySet()){
                try {
                    cv.put(SYNC_TIME_TEXT, System.currentTimeMillis());
                    cv.put(SYNC_TIME_TRANSLATE, System.currentTimeMillis());
                    cv.put(CODE, entry.getKey().toLowerCase());
                    cv.put(FULL_NAME, entry.getValue().toLowerCase());
                    db.insert(tableName, null, cv);
                    cv.clear();
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
            }
        }

    }



}
