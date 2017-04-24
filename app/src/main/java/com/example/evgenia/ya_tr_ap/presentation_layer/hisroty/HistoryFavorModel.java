package com.example.evgenia.ya_tr_ap.presentation_layer.hisroty;

/**
 * Created by User on 14.04.2017.
 */

public class HistoryFavorModel {
    private String text;
    private String translate;
    private String dictionary;
    private String lang;
    private int history;
    private int favorites;


    public HistoryFavorModel(String text, String translate, String dictionary, String lang, int history, int favorites) {
        this.text = text;
        this.translate = translate;
        this.dictionary = dictionary;
        this.lang = lang;
        this.history = history;
        this.favorites = favorites;
    }

    public HistoryFavorModel(String text, String translate, String lang, int history, int favorites) {
        this.text = text;
        this.translate = translate;
        this.lang = lang;
        this.history = history;
        this.favorites = favorites;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }
}
