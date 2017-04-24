package com.example.evgenia.ya_tr_ap.presentation_layer.translate;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.BaseDictionary;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Def;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.translate.BaseTranslate;
import com.example.evgenia.ya_tr_ap.domain_layer.history.HistoryRx;
import com.example.evgenia.ya_tr_ap.domain_layer.translate.TranslateRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 11.04.2017.
 */

public class TranslatePresenter extends Presenter<TranslateContract.ITranslateView> implements TranslateContract.ITranslatePresenter {


    private static final String TAG = "TranslatePresenter";

    @Override
    public void translateText(String text, String langFrom, String langTo) {
        text = encode(text);
        StringBuilder sb = new StringBuilder(langFrom).append("-").append(langTo);

        TranslateRx.getTranslationFromNet(text, sb.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())


                .subscribe(new Subscriber<BaseTranslate>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(TAG, "onError: " + e.getMessage());
//                        e.printStackTrace();
                        unsubscribe();
                    }

                    @Override
                    public void onNext(BaseTranslate baseTranslate) {
                        if(baseTranslate.getCode() != TranslateRx.ERR_OK){
                            showErrorDialog(baseTranslate.getCode());
                        }else {
                            showTranslate(baseTranslate.getText());
                        }
                    }
                });


        TranslateRx.getDictionaryFromNet(text, sb.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseDictionary>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        e.printStackTrace();
                        unsubscribe();
//                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(BaseDictionary baseDictionary) {
                        showDictionary(baseDictionary.getDef());
                    }
                });
    }

    @Override
    public void addToHistory(HistoryFavorModel model) {
        HistoryRx.addToHistoryPart(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        unsubscribe();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });
    }

    @Override
    public void addToFavor(HistoryFavorModel model) {
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

    private void showErrorDialog(int code){
        if(getView() != null){
            getView().showErrorDialog(TranslateRx.getMessage(code), "");
        }else {
            Log.d(TAG, "showErrorDialog: view detached");
        }
    }


    private void showTranslate(List<String> list){
        if(getView() != null){
            getView().showMainTranslate(list);
        }else {
            Log.d(TAG, "showTranslate: view detached");
        }
    }

    private void showDictionary(List<Def> list){
        if(getView() != null){
            getView().showDictionary(list);
        }else {
            Log.d(TAG, "showDictionary: view detached");
        }
    }

    public boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo mobileNetwork = cm.getActiveNetworkInfo();

        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }else return false;
    }

    private String encode(String text){
        text = text.replaceAll("\\;", "%3B");
        text = text.replaceAll("\\n", "%20");
        text = text.replaceAll("\\n\\r", "%20");
        return text.replaceAll("\\+", "%2B");
    }
}
