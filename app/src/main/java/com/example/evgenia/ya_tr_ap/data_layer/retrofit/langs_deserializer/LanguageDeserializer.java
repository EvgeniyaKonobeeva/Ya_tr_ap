package com.example.evgenia.ya_tr_ap.data_layer.retrofit.langs_deserializer;

import com.example.evgenia.ya_tr_ap.presentation_layer.languages_dialogs.models.Language;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by User on 20.04.2017.
 */

public class LanguageDeserializer implements JsonDeserializer<ArrayList<Language>> {
    @Override
    public ArrayList<Language> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ArrayList<Language> list = new ArrayList<>();
        if (json.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();

            for(Map.Entry<String, JsonElement> entry : entries){
                list.add(new Language(entry.getKey().toLowerCase(), entry.getValue().getAsString().toLowerCase()));
            }
        }

        return list;



    }
}
