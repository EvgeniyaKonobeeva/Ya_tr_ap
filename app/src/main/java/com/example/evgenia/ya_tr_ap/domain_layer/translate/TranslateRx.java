package com.example.evgenia.ya_tr_ap.domain_layer.translate;

import android.support.annotation.IntDef;
import android.util.Log;

import com.example.evgenia.ya_tr_ap.data_layer.retrofit.RetrofitCreator;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.BaseDictionary;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.translate.BaseTranslate;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Observable;

/**
 * Created by User on 22.04.2017.
 */

public class TranslateRx {
    private static final String TAG = "TranslateRx";


    public static final int ERR_OK = 200;
    public static final int ERR_KEY_INVALID= 401;
    public static final int ERR_KEY_BLOCKED = 402;
    public static final int ERR_DAILY_REQ_LIMIT_EXCEEDED = 403;
    public static final int ERR_DAILY_TRANSLATE_TEXT_LIMIT_EXCEEDED = 404;
    public static final int ERR_TEXT_TOO_LONG= 413;
    public static final int ERR_TEXT_CANT_TRANSLATE= 422;
    public static final int ERR_LANG_NOT_SUPPORTED= 501;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ERR_OK, ERR_KEY_INVALID, ERR_KEY_BLOCKED, ERR_DAILY_REQ_LIMIT_EXCEEDED,
            ERR_DAILY_TRANSLATE_TEXT_LIMIT_EXCEEDED, ERR_TEXT_TOO_LONG, ERR_TEXT_CANT_TRANSLATE,
            ERR_LANG_NOT_SUPPORTED
    })
    public @interface AnswerCode{}

    public static String getMessage(@AnswerCode int code){
        switch (code){
            case ERR_OK:
                return "Операция выполнена успешно";
            case ERR_KEY_INVALID:
                return "Неправильный API-ключ";
            case ERR_DAILY_TRANSLATE_TEXT_LIMIT_EXCEEDED:
                return "Превышено суточное ограничение на объем переведенного текста";
            case ERR_KEY_BLOCKED:
                return "API-ключ заблокирован";
            case ERR_TEXT_TOO_LONG:
                return "Превышен максимально допустимый размер текста";
            case ERR_TEXT_CANT_TRANSLATE:
                return "Текст не может быть переведен";
            case ERR_LANG_NOT_SUPPORTED:
                return "Заданное направление перевода не поддерживается";
            case ERR_DAILY_REQ_LIMIT_EXCEEDED:
                return "Превышено суточное ограничение на количество запросов";
            default: return "";

        }
    }

    public static Observable<BaseTranslate> getTranslationFromNet(String text, String lang){
        String[] params = new String[]{
                text,
                lang
        };
        return Observable.just(params)
                .flatMap(strings -> RetrofitCreator.createRetrofitTranslate().getTranslation(Utils.API_KEY, strings[0], strings[1]));
    }


    public static Observable<BaseDictionary> getDictionaryFromNet(String text, String lang){
        String[] params = new String[]{
                text,
                lang
        };
        return Observable.just(params).flatMap(strings ->  RetrofitCreator.createRetrofitDictionary().getDictionary(Utils.API_KEY_DICTIONARY, text, lang, "ru"));
    }

}
