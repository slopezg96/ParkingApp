package com.example.santiagolopez.parkingapp.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ServicesFactory {

    public static IServices getInstance() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.151:8090/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IServices.class);
    }

}
