package com.opannapo.googlebooks.core.repository;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by napouser on 23,February,2020
 */
public interface EndPointInterface {
    //https://www.googleapis.com/books/v1/volumes?q=sekolah&startIndex=0&maxResults=2

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @GET("volumes?")
    Call<JsonObject> getSearch(@Query("q") String q,
                               @Query("startIndex") int startIndex,
                               @Query("maxResults") int maxResults);
}