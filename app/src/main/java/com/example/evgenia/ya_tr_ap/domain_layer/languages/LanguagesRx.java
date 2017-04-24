package com.example.evgenia.ya_tr_ap.domain_layer.languages;

import com.example.evgenia.ya_tr_ap.data_layer.Data;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.Language;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.SupportedLangs;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.LanguagesDialog;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;

import static com.example.evgenia.ya_tr_ap.data_layer.retrofit.RetrofitCreator.createRetrofitLangs;

/**
 * Created by User on 18.04.2017.
 */

public class LanguagesRx {


    private static final String TAG = "LanguagesRx";

    public static Observable<ArrayList<Language>> getSupportedLanguagesFromDb(@LanguagesDialog.DialogType int type){
        if(type == LanguagesDialog.TEXT_LANGUAGE){
            return Observable
                    .defer(() -> Observable.just(Data.getLanguages().getAllTextLangs())
                                    .map(LanguagesMapping::getLangsFromCursorWithTime)
                        );
        }else {
            return Observable
                    .defer(() -> Observable.just(Data.getLanguages().getAllTranslateLangs())
                                    .map(LanguagesMapping::getLangsFromCursorWithTime)
                        );
        }

    }

    public static Observable<ArrayList<Language>> getSupportedLanguagesFromNet(){

        return Observable
                .defer(() -> createRetrofitLangs().getLanguages(Utils.API_KEY, "ru")
                            .timeout(30, TimeUnit.SECONDS)
                            .map(SupportedLangs::getLanguage)
                        );
    }

}
