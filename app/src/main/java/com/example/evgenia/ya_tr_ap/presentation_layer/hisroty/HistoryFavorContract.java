package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty;

import java.util.ArrayList;

/**
 * Created by User on 13.04.2017.
 */

public interface HistoryFavorContract {
    interface IHistoryFavorPresenter{


        void updateBdHistory(HistoryFavorModel model);

        void updateBdFavorites(HistoryFavorModel model);

        void downLoadAllHistory();

        void downLoadFavorites();
        void clearFavor();
        void clearHistory();
        void addToHistory(HistoryFavorModel model);



    }

    interface IHistoryFavorView{

        void showItems(ArrayList<HistoryFavorModel> list);

    }
}
