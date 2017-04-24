package com.example.evgenia.ya_tr_ap.data_layer.retrofit;

import com.example.evgenia.ya_tr_ap.data_layer.retrofit.langs_deserializer.LanguageDeserializer;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 20.04.2017.
 */

public class RetrofitCreator {


    private static OkHttpClient okHttpClient() {

        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private static Retrofit retrofitLangs = new Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create( new GsonBuilder()
                    .registerTypeAdapter(ArrayList.class, new LanguageDeserializer()).create()))
            .build();

    public static IGetRetrofitResponse createRetrofitLangs(){
        return retrofitLangs.create(IGetRetrofitResponse.class);
    }


    private static Retrofit retrofitTranslate  = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IGetRetrofitResponse createRetrofitTranslate(){
        return retrofitTranslate.create(IGetRetrofitResponse.class);
    }

    private static Retrofit retrofitDictionary  = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(Utils.BASE_URL_DICTIONARY)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IGetRetrofitResponse createRetrofitDictionary(){
        return retrofitDictionary.create(IGetRetrofitResponse.class);
    }

}
