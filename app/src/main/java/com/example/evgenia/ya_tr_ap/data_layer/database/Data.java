package com.example.evgenia.ya_tr_ap.data_layer.database;

import com.example.evgenia.ya_tr_ap.data_layer.database.languages.ITableLanguages;
import com.example.evgenia.ya_tr_ap.data_layer.database.languages.TableLanguages;

/**
 * Created by User on 18.04.2017.
 */

public class Data {

    public static ITableLanguages getLanguages(){
        return new TableLanguages();
    }
}
