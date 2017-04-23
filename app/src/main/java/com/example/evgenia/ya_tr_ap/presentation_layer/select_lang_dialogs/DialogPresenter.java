package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs;


import android.util.Log;

import com.example.evgenia.ya_tr_ap.domain_layer.SelectLangsRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;
import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.Language;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Evgenia on 09.04.2017.
 */

public class DialogPresenter extends Presenter<DialogContract.IDialogView> implements DialogContract.IDialogPresenter {
    private static final String TAG = "DialogPresenter";

    @Override
    public void loadLanguages(@SelectLangDialog.DialogType int dtype) {
        SelectLangsRx.getSupportedLanguagesFromDb(dtype)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.d(TAG, "call: " + throwable))
                .subscribe((Action1<ArrayList<Language>>) objects -> {
                    if(getView() != null){
                        getView().showItems(objects);
                    }
                });
    }

    @Override
    public void updateLanguagesDb() {

    }

}
