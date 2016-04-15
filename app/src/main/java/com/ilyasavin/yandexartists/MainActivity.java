package com.ilyasavin.yandexartists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.models.Artist;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.card_recycler_view) RecyclerView mArtistsView;
    @Bind(R.id.toolbar) Toolbar toolbar;
    private ArrayList<Artist> mArtistsList;
    private ArtistsRVAdapter mArtistsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViewElements();

        APIManager.getApiService().getData(callback);

    }

    private void initViewElements() {

        setSupportActionBar(toolbar);
        mArtistsList = new ArrayList<>();
        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mArtistsView.setLayoutManager(layoutManager);
    }

    public Callback<List<Artist>> callback = new Callback<List<Artist>>() {

        @Override
        public void success (List<Artist> artists, Response response2) {

            mArtistsList = new ArrayList<>(artists);
            mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this, mArtistsList);
            mArtistsView.setAdapter(mArtistsRVAdapter);
        }

        @Override
        public void failure (RetrofitError error) {

            Toast.makeText(MainActivity.this,"Response error",Toast.LENGTH_SHORT).show();

        }
    };
}
