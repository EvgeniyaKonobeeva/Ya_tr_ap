package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty;


import android.util.Log;

import com.example.evgenia.ya_tr_ap.domain_layer.history.HistoryRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 13.04.2017.
 */

public class HistoryFavorsPresenter extends Presenter<HistoryFavorContract.IHistoryFavorView> implements HistoryFavorContract.IHistoryFavorPresenter {

    private static final String TAG = "HistoryFavorsPresenter";


    @Override
    public void downLoadAllHistory() {
        HistoryRx.getAllHistory().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<HistoryFavorModel>>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(ArrayList<HistoryFavorModel> historyFavorModels) {
                        if(getView() != null){
                            getView().showItems(historyFavorModels);
                        }
                    }
                });

    }

    @Override
    public void downLoadFavorites() {
        HistoryRx.getAllFavorites().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<HistoryFavorModel>>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(ArrayList<HistoryFavorModel> historyFavorModels) {
                        if(getView() != null){
                            getView().showItems(historyFavorModels);
                        }
                    }
                });

    }

    @Override
    public void updateBdHistory(HistoryFavorModel model) {
        HistoryRx.deleteFromHistory(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }
    @Override
    public void addToHistory(HistoryFavorModel model) {
        HistoryRx.addToHistory(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }

    @Override
    public void updateBdFavorites(HistoryFavorModel model) {
        HistoryRx.updateFavorites(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }

    @Override
    public void clearHistory() {
        HistoryRx.clearHistory()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }

    @Override
    public void clearFavor() {
        HistoryRx.clearFavor()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                        unsubscribe();

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }



}
