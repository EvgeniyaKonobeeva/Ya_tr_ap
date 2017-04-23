package com.example.evgenia.ya_tr_ap.presentation_layer.loader_activity;

/**
 * Created by User on 20.04.2017.
 */

public interface LoaderContract {
    interface ILoaderPresenter {
        void loadLanguagesToDb();

    }
    interface ILoaderView {
        void openMainActivity();
    }
}

