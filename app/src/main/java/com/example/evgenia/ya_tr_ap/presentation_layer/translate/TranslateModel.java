package com.example.evgenia.ya_tr_ap.presentation_layer.translate;

/**
 * Created by User on 11.04.2017.
 */

public class TranslateModel {
    private String textLang;
    private String textLangCode;
    private String translateLang;
    private String translateLangCode;
    private String text;

    public TranslateModel(String textLang, String textLangCode, String translateLang, String translateLangCode, String text) {
        this.textLang = textLang;
        this.textLangCode = textLangCode;
        this.translateLang = translateLang;
        this.translateLangCode = translateLangCode;
        this.text = text;
    }

    public TranslateModel(String textLang, String textLangCode, String translateLang, String translateLangCode) {
        this.textLang = textLang;
        this.textLangCode = textLangCode;
        this.translateLang = translateLang;
        this.translateLangCode = translateLangCode;
    }


    public String getTextLang() {
        return textLang;
    }

    public void setTextLang(String textLang) {
        this.textLang = textLang;
    }

    public String getTextLangCode() {
        return textLangCode;
    }

    public void setTextLangCode(String textLangCode) {
        this.textLangCode = textLangCode;
    }

    public String getTranslateLang() {
        return translateLang;
    }

    public void setTranslateLang(String translateLang) {
        this.translateLang = translateLang;
    }

    public String getTranslateLangCode() {
        return translateLangCode;
    }

    public void setTranslateLangCode(String translateLangCode) {
        this.translateLangCode = translateLangCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
