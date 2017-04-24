package com.example.evgenia.ya_tr_ap.data_layer.retrofit;

import com.example.evgenia.ya_tr_ap.data_layer.retrofit.dicitionary.BaseDictionary;
import com.example.evgenia.ya_tr_ap.data_layer.retrofit.translate.BaseTranslate;
import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.SupportedLangs;
import com.example.evgenia.ya_tr_ap.presentation_layer.utils.Utils;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 20.04.2017.
 */

public interface IGetRetrofitResponse {

    @GET("getLangs")
    Observable<SupportedLangs> getLanguages(
            @Query("key") String apiKey,
            @Query("ui") String ui
    );

    @POST("translate")
    Observable<BaseTranslate> getTranslation(@Query("key") String key,
                                             @Query(value = "text", encoded = true) String text,
                                             @Query("lang") String lang);


    @POST("lookup")
    Observable<BaseDictionary> getDictionary(@Query("key") String key,
                                             @Query(value = "text", encoded = true) String text,
                                             @Query("lang") String lang,
                                             @Query("ui") String ui);




}
