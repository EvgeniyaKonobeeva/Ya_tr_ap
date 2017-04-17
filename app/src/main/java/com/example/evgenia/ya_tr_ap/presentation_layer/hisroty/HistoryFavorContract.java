package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty;

import java.util.ArrayList;

/**
 * Created by User on 13.04.2017.
 */

public interface HistoryFavorContract {
    interface IHistoryFavorPresenter{
        /**
         * запрос по истории
         * запрос по закладкам */

        void updateBdHistory();
        void updateBdFavorites();
        void downLoadAllHistory();
        void downLoadFavorites();

    }

    interface IHistoryFavorView{
        void showItems(ArrayList<HistoryFavorModel> list);
    }
}
