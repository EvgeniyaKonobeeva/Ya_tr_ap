package com.example.evgenia.ya_tr_ap.domain_layer.history;

import android.database.Cursor;

import com.example.evgenia.ya_tr_ap.data_layer.tables.history.TableHistory;
import com.example.evgenia.ya_tr_ap.presentation_layer.hisroty.HistoryFavorModel;

import java.util.ArrayList;

/**
 * Created by User on 24.04.2017.
 */

public class HistoryMapping {
    public static ArrayList<HistoryFavorModel> getHistoryFromCursor(Cursor cursor){
        ArrayList<HistoryFavorModel> list = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){

            int colText = cursor.getColumnIndex(TableHistory.TEXT);
            int colTr = cursor.getColumnIndex(TableHistory.TRANSLATE);
            int colDict = cursor.getColumnIndex(TableHistory.DICTIONARY);
            int colLang = cursor.getColumnIndex(TableHistory.LANG);
            int colHist = cursor.getColumnIndex(TableHistory.HISTORY);
            int colFav = cursor.getColumnIndex(TableHistory.FAVOR);

            while (cursor.moveToNext()){
                 String text = cursor.getString(colText);
                 String translate = cursor.getString(colTr);
                 String dictionary = cursor.getString(colDict);
                 String lang = cursor.getString(colLang);
                 int history = cursor.getInt(colHist);
                 int favorites = cursor.getInt(colFav);


                list.add(new HistoryFavorModel(text, translate, dictionary, lang, history, favorites));
            }


        }
        return list;
    }
}
