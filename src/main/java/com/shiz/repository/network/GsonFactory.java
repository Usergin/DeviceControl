package com.shiz.repository.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.shiz.model.data.event.Call;
import org.springframework.stereotype.Component;

/**
 * Created by oldman on 05.04.17.
 */
public class GsonFactory {
/*
	"date":"2013-02-10T13:45:30+0100",

 */
    public static Gson create() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }
}
