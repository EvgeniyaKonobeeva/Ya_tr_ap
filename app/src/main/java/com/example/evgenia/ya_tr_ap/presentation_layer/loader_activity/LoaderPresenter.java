package com.example.evgenia.ya_tr_ap.presentation_layer.loader_activity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.Data;
import com.example.evgenia.ya_tr_ap.domain_layer.SelectLangsRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.Language;

import java.util.ArrayList;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by User on 20.04.2017.
 */

public class LoaderPresenter extends Presenter <LoaderContract.ILoaderView> implements LoaderContract.ILoaderPresenter {

    private static final String TAG = "LoaderPresenter";

    @Override
    public void loadLanguagesToDb() {
        SelectLangsRx.getSupportedLanguagesFromNet()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ArrayList<Language>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: thread my " + Thread.currentThread().getName());
                        onLoadFinished();
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Throwable=" + e.getMessage());
                        Log.d(TAG, "onError: thread my " + Thread.currentThread().getName());
                        onLoadFinished();
                        unsubscribe();
                    }

                    @Override
                    public void onNext(ArrayList<Language> languages) {
                        Data.getLanguages().insertAllLangs(languages);
                        Log.d(TAG, "onNext: thread my " + Thread.currentThread().getName());
                        onLoadFinished();
                        unsubscribe();
                    }
                });
    }

    private void onLoadFinished(){

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            if(getView() != null){
                getView().openMainActivity();
            }
        });
    }
}
