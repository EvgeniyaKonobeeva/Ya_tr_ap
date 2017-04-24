package com.example.evgenia.ya_tr_ap.domain_layer.history;

import com.example.evgenia.ya_tr_ap.data_layer.Data;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by User on 24.04.2017.
 */

public class HistoryRx {


    public static Observable<ArrayList<HistoryFavorModel>> getAllHistory(){
        return Observable
                .defer(
                () -> Observable.just(Data.getHistory().getHistory()).map(HistoryMapping::getHistoryFromCursor)
        );
    }
    public static Observable<ArrayList<HistoryFavorModel>> getAllFavorites(){
        return Observable
                .defer(
                        () -> Observable.just(Data.getHistory().getFavorites()).map(HistoryMapping::getHistoryFromCursor)
                );
    }

    public static Observable<Boolean> updateFavorites(HistoryFavorModel model){
        return Observable.just(model).map(model1 -> Data.getHistory().addToFavorites(model1));
    }

    public static Observable<Boolean> deleteFromHistory(HistoryFavorModel model){
        return Observable.just(model).map(model1 -> Data.getHistory().deleteFromHistory(model1));
    }

    public static Observable<Boolean> clearHistory(){
        return Observable.defer(() -> Observable.just(Data.getHistory().clearHistory()));
    }

    public static Observable<Boolean> clearFavor(){
        return Observable.defer(() -> Observable.just(Data.getHistory().clearFavorites()));
    }

    public static Observable<Boolean> addToHistory(HistoryFavorModel model){
        return Observable.just(model).map(model1 -> Data.getHistory().addToHistory(model1));
    }

    public static Observable<Boolean> addToHistoryPart(HistoryFavorModel model){
        return Observable.just(model).map(model1 -> Data.getHistory().addToHistoryPart(model1));
    }

}
