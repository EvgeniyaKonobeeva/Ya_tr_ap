package com.example.evgenia.ya_tr_ap.data_layer.retrofit;

import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.Language;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.langs_deserializer.LanguageDeserializer;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 20.04.2017.
 */

public class RetrofitCreator {

    public static Retrofit retrofitLangs = new Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create( new GsonBuilder()
                    .registerTypeAdapter(ArrayList.class, new LanguageDeserializer()).create()))
            .build();

    public static IGetRetrofitResponse createRetrofitLangs(){
        return retrofitLangs.create(IGetRetrofitResponse.class);
    }
}
