package com.example.evgenia.ya_tr_ap.translate.choose_lang_dialogs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Evgenia on 09.04.2017.
 */

public class DialogModel implements Parcelable{
    private String language;
    private String languageCode;
    private boolean isLatestSelected;
    private boolean isSelected;

    public DialogModel(String language, String languageCode, boolean isLatestSelected, boolean isSelected) {
        this.language = language;
        this.languageCode = languageCode;
        this.isLatestSelected = isLatestSelected;
        this.isSelected = isSelected;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public boolean isLatestSelected() {
        return isLatestSelected;
    }

    public void setLatestSelected(boolean latestSelected) {
        isLatestSelected = latestSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    protected DialogModel(Parcel in) {
        language = in.readString();
        languageCode = in.readString();
        isLatestSelected = in.readByte() != 0x00;
        isSelected = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(language);
        dest.writeString(languageCode);
        dest.writeByte((byte) (isLatestSelected ? 0x01 : 0x00));
        dest.writeByte((byte) (isSelected ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DialogModel> CREATOR = new Parcelable.Creator<DialogModel>() {
        @Override
        public DialogModel createFromParcel(Parcel in) {
            return new DialogModel(in);
        }

        @Override
        public DialogModel[] newArray(int size) {
            return new DialogModel[size];
        }
    };
}