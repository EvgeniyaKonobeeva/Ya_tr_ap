package com.example.evgenia.ya_tr_ap.choose_lang_dialogs;

import android.os.Parcel;

import com.example.evgenia.ya_tr_ap.Presenter;
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.DialogContract;
import com.example.evgenia.ya_tr_ap.choose_lang_dialogs.SelectLangDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgenia on 09.04.2017.
 */

public class DialogPresenter extends Presenter<DialogContract.IDialogView> implements DialogContract.IDialogPresenter {
    @Override
    public void loadLanguages(@SelectLangDialog.DialogType int dtype) {
        if(getView() != null){

            getView().showItems(generateList());
        }
    }

    @Override
    public void updateLanguagesDb() {

    }

    public List generateList (){
        List<DialogModel> list = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            DialogModel model = new DialogModel("russ", "ru", false, false);
            list.add(model);
            if(i >=0 && i < 3){
                list.get(i).setLatestSelected(true);
            }
            if(i == 10){
                list.get(i).setSelected(true);
            }
        }
        return list;
    }
}
