package com.example.evgenia.ya_tr_ap.presentation_layer.translate;

/**
 * Created by User on 11.04.2017.
 */

public interface TranslateContract {

    interface ITranslatePresenter{

        TranslateModel translateText();



    }

    interface ITranslateView{

        /**
         * переименовывает табы во фрагменте, сетит TranslateModel во фрагменте*/
        void showLanguages(TranslateModel translateModel);



    }
}
