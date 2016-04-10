package com.ilyasavin.yandexartists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.models.Artist;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {


    ArrayList<Artist> artistsList;
    RecyclerView mArtistsView;
    ArtistsRVAdapter mArtistsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        artistsList = new ArrayList<>();

        mArtistsView = (RecyclerView)findViewById(R.id.card_recycler_view);

        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mArtistsView.setLayoutManager(layoutManager);

        APIManager.getApiService().getData(callback);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Callback<List<Artist>> callback = new Callback<List<Artist>>() {

        @Override
        public void success (List<Artist> artists, Response response2) {

            artistsList = new ArrayList<>(artists);


            mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this,artistsList);

            mArtistsView.setAdapter(mArtistsRVAdapter);




        }

        @Override
        public void failure (RetrofitError error) {

        int a =5;

        }
    };
}
