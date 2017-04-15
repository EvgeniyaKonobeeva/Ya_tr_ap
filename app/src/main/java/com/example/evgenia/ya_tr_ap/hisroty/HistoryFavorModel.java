package com.example.evgenia.ya_tr_ap.hisroty;

/**
 * Created by User on 14.04.2017.
 */

public class HistoryFavorModel {
    private String lang;
    private String text;
    private String translateMain;
    private boolean isMarked;
    private String historyTranslate;

    public HistoryFavorModel(String lang, String text, String translateMain, boolean isMarked, String historyTranslate) {
        this.lang = lang;
        this.text = text;
        this.translateMain = translateMain;
        this.isMarked = isMarked;
        this.historyTranslate = historyTranslate;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslateMain() {
        return translateMain;
    }

    public void setTranslateMain(String translateMain) {
        this.translateMain = translateMain;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public String getHistoryTranslate() {
        return historyTranslate;
    }

    public void setHistoryTranslate(String historyTranslate) {
        this.historyTranslate = historyTranslate;
    }
}
