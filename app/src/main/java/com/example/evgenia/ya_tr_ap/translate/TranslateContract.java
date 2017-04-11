package com.example.evgenia.ya_tr_ap.translate;

/**
 * Created by User on 11.04.2017.
 */

public interface TranslateContract {

    interface ITranslatePresenter{

        /**
         * метод делает запрос к бд к таблицам языков
         * @return объект класса {@link TranslateModel} с выбранными языками*/
        TranslateModel getLanguages();
        TranslateModel translateText();


    }

    interface ITranslateView{

        /**
         * переименовывает табы во фрагменте, сетит TranslateModel во фрагменте*/
        void showLanguages(TranslateModel translateModel);



    }
}
