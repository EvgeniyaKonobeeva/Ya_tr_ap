package com.example.evgenia.ya_tr_ap.hisroty;

import com.example.evgenia.ya_tr_ap.Presenter;

import java.util.ArrayList;

/**
 * Created by User on 13.04.2017.
 */

public class HistoryFavorsPresenter extends Presenter<HistoryFavorContract.IHistoryFavorView> implements HistoryFavorContract.IHistoryFavorPresenter {

    @Override
    public void updateBdHistory() {

    }

    @Override
    public void downLoadAllHistory() {
        if(getView() != null){
            getView().showItems(generateItems(false));
        }
    }

    @Override
    public void downLoadFavorites() {
        if(getView() != null){
            getView().showItems(generateItems(true));
        }
    }

    @Override
    public void updateBdFavorites() {

    }

    private ArrayList<HistoryFavorModel> generateItems(boolean allSelected){
        ArrayList<HistoryFavorModel> list = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            if(i%3 == 0 || allSelected) {
                list.add(new HistoryFavorModel("rus-en", "text text ! " + i, "translate translate translate !", allSelected, ""));
            }else {
                list.add(new HistoryFavorModel("en-rus", "text text ! " + i, "translate translate translate !", !allSelected, ""));
            }
        }

        return list;
    }

}
