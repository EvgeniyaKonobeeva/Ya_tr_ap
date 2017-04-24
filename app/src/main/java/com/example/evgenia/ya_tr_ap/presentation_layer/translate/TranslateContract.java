package com.example.evgenia.ya_tr_ap.presentation_layer.translate;

import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.Def;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.translate.BaseTranslate;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.List;

/**
 * Created by User on 11.04.2017.
 */

public interface TranslateContract {

    interface ITranslatePresenter{

        void translateText(String text, String langFrom, String langTo);
        void addToHistory(HistoryFavorModel model);
        void addToFavor(HistoryFavorModel model);




    }

    interface ITranslateView{

        void showMainTranslate(List<String> list);
        void showDictionary(List<Def> list);
        void showErrorDialog(String msg, String title);



    }
}
