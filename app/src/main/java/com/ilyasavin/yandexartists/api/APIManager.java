package com.ilyasavin.yandexartists.api;

import com.ilyasavin.yandexartists.models.Artist;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class APIManager {

    private static ApiService apiService;
    private static final String URL = "http://download.cdn.yandex.net/mobilization-2016";

    public interface ApiService {

        @GET("/artists.json")
        void getData(Callback<List<Artist>> response);

    }

    public static ApiService getApiService() {

        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(URL).build();

        apiService = restAdapter.create(ApiService.class);

        return apiService;


    }

}