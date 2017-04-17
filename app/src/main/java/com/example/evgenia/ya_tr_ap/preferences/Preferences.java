package com.example.evgenia.ya_tr_ap.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by User on 17.04.2017.
 */

public class Preferences {
    public static final String FILE_PREFERENCES = "preferences_file";

    public enum EnumKeys{

        ENTER_TEXT_LANG(TYPE_STRING),
        TRANSLATE_TEXT_LANG(TYPE_STRING);



        @Preferences.PreferenceType int typeVal;
        EnumKeys(@Preferences.PreferenceType int type){
            typeVal = type;
        }
        public @Preferences.PreferenceType int getType(){
            return typeVal;
        }

    }




    public static final int TYPE_INT = 0;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_LONG = 4;
    public static final int TYPE_BOOLEAN = 5;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_INT, TYPE_FLOAT, TYPE_STRING, TYPE_LONG,TYPE_BOOLEAN})
    public @interface PreferenceType{}



    public static SharedPreferences preferences;

    public static void createPreferences(Context context){
        preferences = context.getSharedPreferences(FILE_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void putPreference(EnumKeys key, Object value){
        SharedPreferences.Editor e = preferences.edit();

        switch (key.getType()){
            case TYPE_STRING:
                e.putString(key.name(), (String)value);
                break;

            case TYPE_BOOLEAN:
                e.putBoolean(key.name(), (boolean)value);
                break;

            case TYPE_FLOAT:
                e.putFloat(key.name(), (float)value);
                break;

            case TYPE_INT:
                e.putInt(key.name(), (int)value);
                break;

            case TYPE_LONG:
                e.putLong(key.name(), (long)value);
                break;
        }

        e.apply();
    }

    public static Object getPreference(EnumKeys key){
        switch (key.getType()){
            case TYPE_STRING:
                return  preferences.getString(key.name(), "");

            case TYPE_BOOLEAN:
                return preferences.getBoolean(key.name(), false);

            case TYPE_FLOAT:
                return preferences.getFloat(key.name(), 0f);

            case TYPE_INT:
                return preferences.getInt(key.name(), 0);

            default:
                return preferences.getLong(key.name(), 0);
        }
    }


}
