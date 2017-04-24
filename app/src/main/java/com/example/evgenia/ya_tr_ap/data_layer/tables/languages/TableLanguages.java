package com.example.evgenia.ya_tr_ap.data_layer.tables.languages;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.StringDef;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.domain_layer.languages.LanguagesMapping;
import com.example.evgenia.ya_tr_ap.presentation_layer.MyApp;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.Language;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;


/**
 * Created by User on 18.04.2017.
 */

public class TableLanguages implements ITableLanguages{
    public static final String tableName = "TableLanguages";
    public static final String TAG = "TableLanguages";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SYNC_TIME_TEXT, SYNC_TIME_TRANSLATE, CODE, FULL_NAME})
    public @interface Columns{}
    public static final String SYNC_TIME_TEXT = "SYNC_TIME_TEXT";
    public static final String SYNC_TIME_TRANSLATE = "SYNC_TIME_TRANSLATE";
    public static final String CODE = "CODE";
    public static final String FULL_NAME = "FULL_NAME";
    public static final String TIME = "TIME";
    SQLiteDatabase db;


    public Cursor getAllTextLangs(){
        db = MyApp.getDb();
        StringBuffer query = new StringBuffer();
        query.append("select ").append(FULL_NAME).append(", ").append(CODE).append(" , ")
                .append(SYNC_TIME_TEXT).append(" as ").append(TIME)
                .append(" from ").append(tableName)
                .append(" order by ").append(FULL_NAME).append(";");
        if(db != null) {
            return db.rawQuery(query.toString(), null);
        }else return  null;
    }

    public Cursor getAllTranslateLangs(){
        db = MyApp.getDb();
        String query = "select " + FULL_NAME + ", " + CODE + " , " + SYNC_TIME_TRANSLATE + " as " + TIME
                + " from " + tableName
                + " order by " + FULL_NAME
                + ";" ;
        if(db != null) {
            return db.rawQuery(query, null);
        }else return  null;
    }


    public String updateSelectedTextLangs(String selected){
        db = MyApp.getDb();
        selected = selected.toLowerCase();
        String query = " update " + tableName
                + " set " + SYNC_TIME_TEXT + " = " + System.currentTimeMillis()
                + " where " + CODE + " like '" + selected + "' ;";

        String selectQuery = " select " + FULL_NAME
                + " from " + tableName
                + " where " + CODE + " like '" + selected + "' ;";



        if(db != null) {
            db.beginTransaction();

            Cursor cursor = db.rawQuery(query, null);

            Cursor selectCursor = db.rawQuery(selectQuery, null);


            if (cursor != null && selectCursor != null) {
                cursor.moveToNext();
                cursor.close();
                db.setTransactionSuccessful();
                db.endTransaction();

                return LanguagesMapping.getFullNameFromCursor(selectCursor);

            }else{
                db.endTransaction();
                Log.d(TAG, "updateSelectedTextLangs: something goes wrong");
                return "";
            }
        }else
            return "";
    }


    public String updateSelectedTranslateLangs(String selected){
        db = MyApp.getDb();

        selected = selected.toLowerCase();

        String updateQuery = " update " + tableName
                + " set " + SYNC_TIME_TRANSLATE + " = " + System.currentTimeMillis()
                + " where " + CODE + " like '" + selected + "' ;";

        String selectQuery = " select " + FULL_NAME
                + " from " + tableName
                + " where " + CODE + " like '" + selected + "' ;";

        if(db != null) {
            db.beginTransaction();

            Cursor cursor = db.rawQuery(updateQuery, null);

            Cursor selectCursor = db.rawQuery(selectQuery, null);


            if (cursor != null && selectCursor != null) {
                cursor.moveToNext();
                cursor.close();
                db.setTransactionSuccessful();
                db.endTransaction();

                return LanguagesMapping.getFullNameFromCursor(selectCursor);

            }else{
                db.endTransaction();
                Log.d(TAG, "updateSelectedTextLangs: something goes wrong");
                return "";
            }
        }else
            return "";
    }



    public void insertAllLangs(ArrayList<Language> langs){
        ArrayList <Language> langInTable = new ArrayList<>();
        String selectQuery = "select " + FULL_NAME + ", " + CODE
                + " from " + tableName + " ;" ;
        SQLiteDatabase db = MyApp.getDb();
        if(db != null){
            Cursor selectCursor = db.rawQuery(selectQuery, null);
            langInTable = LanguagesMapping.getLangsFromCursor(selectCursor);
            selectCursor.close();
        }

        for(int i = 0; i < langs.size(); i++){
            if(langInTable.contains(langs.get(i))){
                langs.remove(langs.get(i));
                i--;
            }
        }

        ContentValues cv = new ContentValues();

        if(db != null){
            db.beginTransaction();
            try {
                for (Language lang : langs){

                        cv.put(SYNC_TIME_TEXT, 0);
                        cv.put(SYNC_TIME_TRANSLATE, 0);
                        cv.put(CODE, lang.getCode().toLowerCase());
                        cv.put(FULL_NAME, lang.getLanguage().toLowerCase());
                        db.insert(tableName, null, cv);
                        cv.clear();
                }
            }finally {
                Log.d(TAG, "insertAllLangs: ");
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }

    }



}
