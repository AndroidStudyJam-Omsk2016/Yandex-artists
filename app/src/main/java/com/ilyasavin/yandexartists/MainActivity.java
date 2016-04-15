package com.ilyasavin.yandexartists;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.views.MaterialDrawer;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    private ArrayList<Artist> mArtistsList;
    private RecyclerView mArtistsView;
    private ArtistsRVAdapter mArtistsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        initViewElements();

        APIManager.getApiService().getData(callback);

    }

    private void initViewElements() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MaterialDrawer materialDrawer = new MaterialDrawer();
        materialDrawer.initDrawer(toolbar,this);
        mArtistsList = new ArrayList<>();
        mArtistsView = (RecyclerView)findViewById(R.id.card_recycler_view);
        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mArtistsView.setLayoutManager(layoutManager);
        mArtistsView.setOnScrollListener(onScrollListener);

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

    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        boolean hideToolBar = false;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (hideToolBar) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 20) {
                hideToolBar = true;

            } else if (dy < -5) {
                hideToolBar = false;
            }
        }
    };

}
