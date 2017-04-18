package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs;


import android.util.Log;

import com.example.evgenia.ya_tr_ap.domain_layer.languages.SelectLangsRx;
import com.example.evgenia.ya_tr_ap.presentation_layer.Presenter;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Evgenia on 09.04.2017.
 */

public class DialogPresenter extends Presenter<DialogContract.IDialogView> implements DialogContract.IDialogPresenter {
    private static final String TAG = "DialogPresenter";

    @Override
    public void loadLanguages(@SelectLangDialog.DialogType int dtype) {
        SelectLangsRx.getSupportedLanguages(dtype)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call: " + throwable);
                    }
                })
                .subscribe(new Action1<ArrayList<DialogModel>>() {
                    @Override
                    public void call(ArrayList<DialogModel> objects) {
                        if(getView() != null){
                            getView().showItems(objects);
                        }
                    }
                });
    }

    @Override
    public void updateLanguagesDb() {


    }

    public List generateList (){
        List<Object> list = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            if(i== 0){
                list.add("Недавние языки");
            }else if(i == 3){
                list.add("все языки");
            } else {
                if(i%2 == 0) {
                    DialogModel model = new DialogModel("russ", "ru", false, false);
                    list.add(model);
                }else {
                    DialogModel model = new DialogModel("english", "en", false, false);
                    list.add(model);
                }
                if (i > 0 && i < 3) {
                    ((DialogModel)list.get(i)).setLatestSelected(true);
                }
                if (i == 10) {
                    ((DialogModel)list.get(i)).setSelected(true);
                }
            }
        }
        return list;
    }
}
