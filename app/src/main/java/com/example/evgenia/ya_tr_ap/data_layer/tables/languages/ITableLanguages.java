package com.example.evgenia.ya_tr_ap.data_layer.tables.languages;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.Language;

import java.util.ArrayList;

/**
 * Created by User on 19.04.2017.
 */

public interface ITableLanguages {
    Cursor getAllTextLangs();
    Cursor getAllTranslateLangs();
    String updateSelectedTextLangs(String selected);
    String updateSelectedTranslateLangs(String selected);
    void insertAllLangs(ArrayList<Language> list);
}
