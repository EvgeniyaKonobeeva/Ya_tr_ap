package com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models;

import android.support.annotation.NonNull;

/**
 * Created by User on 20.04.2017.
 */

public class Language implements Comparable<Language>{
    private String code;
    private String language;
    private long synkTime = 0;
    private boolean selected = false;
    private boolean recent = false;

    public Language(String code, String language) {
        this.code = code;
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getSynkTime() {
        return synkTime;
    }

    public void setSynkTime(long synkTime) {
        this.synkTime = synkTime;
    }

    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    @Override
    public int compareTo(@NonNull Language o) {
        /* для сортировки по убыванию*/
        if (this.synkTime > o.getSynkTime())
            return 1;
        else if (this.synkTime < o.getSynkTime())
            return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Language) {
            Language language = (Language) obj;
            if (language.getCode().equals(this.code) && language.getLanguage().equals(this.language)) {
                return true;
            } else return false;
        }else return false;
    }

    @Override
    public int hashCode() {
        return (this.language + this.code).hashCode();
    }


}
