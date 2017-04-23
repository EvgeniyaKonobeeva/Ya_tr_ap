package com.example.evgenia.ya_tr_ap.presentation_layer.translate;


import android.util.Log;

import com.example.evgenia.ya_tr_ap.domain_layer.TranslateRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.SelectLangDialog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 11.04.2017.
 */

public class TranslatePresenter extends Presenter<TranslateContract.ITranslateView> implements TranslateContract.ITranslatePresenter {


    private static final String TAG = "TranslatePresenter";

    @Override
    public TranslateModel translateText() {
        return null;
    }
}
