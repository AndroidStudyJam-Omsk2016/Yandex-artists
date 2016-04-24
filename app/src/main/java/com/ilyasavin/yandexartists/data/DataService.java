package com.ilyasavin.yandexartists.data;

import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.models.Artist;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A singleton class for interaction with {@link APIManager}.
 */
public class DataService {

    private static DataService dataService;

    public static DataService init() {
        if (dataService == null) {
            dataService = new DataService();
        }
        return dataService;
    }

    public void getDataFromServer(final onArtistsResult listener) {

        APIManager.getApiService().getData(new Callback<List<Artist>>() {

            @Override
            public void success(List<Artist> artists, Response response2) {

                listener.onArtistsResult(artists);

            }

            @Override
            public void failure(RetrofitError error) {


            }
        });
    }

    ;


    public static interface onArtistsResult {
        public void onArtistsResult(List<Artist> artists);
    }
}




