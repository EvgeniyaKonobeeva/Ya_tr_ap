package com.example.evgenia.ya_tr_ap.domain_layer.languages;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.data_layer.database.languages.TableLanguages;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.DialogModel;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;

/**
 * Created by User on 19.04.2017.
 */

public class LanguagesMapping {

    public static ArrayList<DialogModel> getLangsFromCursor(Cursor cursor){
        if(cursor != null && cursor.getCount() > 0){

            ArrayList<DialogModel> list = new ArrayList<>();

            int fullNameCol = cursor.getColumnIndex(TableLanguages.FULL_NAME);
            int codeCol = cursor.getColumnIndex(TableLanguages.CODE);

            while (cursor.moveToNext()){
                DialogModel dialogModel = new DialogModel(cursor.getString(fullNameCol), cursor.getString(codeCol));
                list.add(dialogModel);
            }
            return list;
        }else {
            return new ArrayList<>();
        }
    }

}
