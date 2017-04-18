package com.example.evgenia.ya_tr_ap.domain_layer.languages;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.data_layer.database.Data;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.DialogModel;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.SelectLangDialog;

import java.util.ArrayList;
import java.util.Objects;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by User on 18.04.2017.
 */

public class SelectLangsRx {


    public static Observable<ArrayList<DialogModel>> getSupportedLanguages(@SelectLangDialog.DialogType int type){
        if(type == SelectLangDialog.TEXT_LANGUAGE){
            return Observable.just(Data.getLanguages().getAllTextLangs())
                    .map(new Func1<Cursor, ArrayList<DialogModel>>() {
                        @Override
                        public ArrayList<DialogModel> call(Cursor cursor) {
                            return LanguagesMapping.getLangsFromCursor(cursor);
                        }
                    });
        }else {
            return Observable.just(Data.getLanguages().getAllTranslateLangs())
                    .map(new Func1<Cursor, ArrayList<DialogModel>>() {
                        @Override
                        public ArrayList<DialogModel> call(Cursor cursor) {
                            return LanguagesMapping.getLangsFromCursor(cursor);
                        }
                    });
        }

    }

}
