package com.example.evgenia.ya_tr_ap.data_layer.retrofit;

import com.example.evgenia.ya_tr_ap.presentation_layer.select_lang_dialogs.models.SupportedLangs;

import retrofit2.http.GET;
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
}
