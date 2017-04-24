package com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs;

import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.Language;

import java.util.ArrayList;

/**
 * Created by Evgenia on 09.04.2017.
 */

public interface DialogContract {
    interface IDialogPresenter{
        void loadLanguages(@LanguagesDialog.DialogType int dtype);
        void updateLanguagesDb();
    }

    interface IDialogView{
        void showItems(ArrayList<Language> itemList);
    }

}
