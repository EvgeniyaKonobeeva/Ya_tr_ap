package com.example.evgenia.ya_tr_ap.data_layer.database.languages;

import android.database.Cursor;

import java.util.HashMap;

/**
 * Created by User on 19.04.2017.
 */

public interface ITableLanguages {
    public Cursor getAllTextLangs();
    Cursor getAllTranslateLangs();
    void updateSelectedTextLangs(String selected);
    void updateSelectedTranslateLangs(String selected);
    void insertAllLangs(HashMap<String, String> map);
}
