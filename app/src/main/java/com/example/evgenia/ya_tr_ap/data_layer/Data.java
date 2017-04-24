package com.example.evgenia.ya_tr_ap.data_layer;


import com.example.evgenia.ya_tr_ap.data_layer.tables.history.ITableHistory;
import com.example.evgenia.ya_tr_ap.data_layer.tables.history.TableHistory;
import com.example.evgenia.ya_tr_ap.data_layer.tables.languages.ITableLanguages;
import com.example.evgenia.ya_tr_ap.data_layer.tables.languages.TableLanguages;

/**
 * Created by User on 18.04.2017.
 */

public class Data {

    public static ITableLanguages getLanguages(){
        return new TableLanguages();
    }


    public static ITableHistory getHistory(){
        return new TableHistory();
    }
}
