package com.example.evgenia.ya_tr_ap.data_layer.database.languages;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.presentation_layer.MyApp;

/**
 * Created by User on 18.04.2017.
 */

public class QueryLangs {
    private TableLanguages tableLanguages;


    public Cursor getAllLanguages(){
        return MyApp.getDb().rawQuery(tableLanguages.getAllLangs(), null);
    }
}
