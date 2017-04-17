package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs;

import java.util.List;

/**
 * Created by Evgenia on 09.04.2017.
 */

public interface DialogContract {
    interface IDialogPresenter{
        void loadLanguages(@SelectLangDialog.DialogType int dtype);
        void updateLanguagesDb();
    }

    interface IDialogView{
        void showItems(List<DialogModel> itemList);
    }

}
