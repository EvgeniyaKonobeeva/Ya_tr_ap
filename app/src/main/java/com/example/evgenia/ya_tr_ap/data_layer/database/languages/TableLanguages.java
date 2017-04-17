package com.example.evgenia.ya_tr_ap.data_layer.database.languages;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by User on 18.04.2017.
 */

public class TableLanguages {
    public static final String tableName = "TableLanguages";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SYNC_TIME_TEXT, SYNC_TIME_TRANSLATE, CODE, FULL_NAME})
    public @interface Columns{}
    public static final String SYNC_TIME_TEXT = "SYNC_TIME_TEXT";
    public static final String SYNC_TIME_TRANSLATE = "SYNC_TIME_TRANSLATE";
    public static final String CODE = "CODE";
    public static final String FULL_NAME = "FULL_NAME";

    public String getAllLangs(){
        return "";
    }



}
