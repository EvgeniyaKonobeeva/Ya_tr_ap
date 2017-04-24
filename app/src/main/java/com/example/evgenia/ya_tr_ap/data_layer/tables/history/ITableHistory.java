package com.example.evgenia.ya_tr_ap.data_layer.tables.history;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

/**
 * Created by User on 24.04.2017.
 */

public interface ITableHistory {
    Cursor getHistory();
    Cursor getFavorites();
    boolean addToFavorites(HistoryFavorModel model);
    boolean addToHistory(HistoryFavorModel model);
    boolean deleteFromHistory (HistoryFavorModel model);
    boolean clearHistory();
    boolean clearFavorites();

    boolean addToHistoryPart(HistoryFavorModel model);


}
