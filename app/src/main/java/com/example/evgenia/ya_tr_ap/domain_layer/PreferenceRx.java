package com.example.evgenia.ya_tr_ap.domain_layer;

import com.example.evgenia.ya_tr_ap.data_layer.Data;

import rx.Observable;

/**
 * Created by User on 23.04.2017.
 */

public class PreferenceRx {
    public static Observable<String> updateTextLanguage(String langsCode){
        return Observable.defer(() -> Observable.just(Data.getLanguages().updateSelectedTextLangs(langsCode)));
    }

    public static Observable<String> updateTranslateLanguage(String langsCode){
        return Observable.defer(() -> Observable.just(Data.getLanguages().updateSelectedTranslateLangs(langsCode)));
    }
}
