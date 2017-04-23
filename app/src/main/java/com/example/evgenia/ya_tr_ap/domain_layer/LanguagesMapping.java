package com.example.evgenia.ya_tr_ap.domain_layer;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.data_layer.languages.TableLanguages;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.Language;

import java.util.ArrayList;

/**
 * Created by User on 19.04.2017.
 */

public class LanguagesMapping {

    public static ArrayList<Language> getLangsFromCursorWithTime(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {

            ArrayList<Language> list = new ArrayList<>();

            int fullNameCol = cursor.getColumnIndex(TableLanguages.FULL_NAME);
            int codeCol = cursor.getColumnIndex(TableLanguages.CODE);
            int timeCol = cursor.getColumnIndex(TableLanguages.TIME);

            while (cursor.moveToNext()) {
                Language dialogModel = new Language(cursor.getString(codeCol),cursor.getString(fullNameCol));
                dialogModel.setSynkTime(cursor.getLong(timeCol));
                list.add(dialogModel);
            }
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public static ArrayList<Language> getLangsFromCursor(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {

            ArrayList<Language> list = new ArrayList<>();

            int fullNameCol = cursor.getColumnIndex(TableLanguages.FULL_NAME);
            int codeCol = cursor.getColumnIndex(TableLanguages.CODE);
            int timeCol = cursor.getColumnIndex(TableLanguages.TIME);

            while (cursor.moveToNext()) {
                Language dialogModel = new Language(cursor.getString(codeCol),cursor.getString(fullNameCol));
                dialogModel.setSynkTime(cursor.getLong(timeCol));
                list.add(dialogModel);
            }
            cursor.close();
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public static String getFullNameFromCursor(Cursor cursor){
        cursor.moveToFirst();
        int fullNameCol = cursor.getColumnIndex(TableLanguages.FULL_NAME);
        return cursor.getString(fullNameCol);

    }



}