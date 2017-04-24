
package com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Syn extends Object{

    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
