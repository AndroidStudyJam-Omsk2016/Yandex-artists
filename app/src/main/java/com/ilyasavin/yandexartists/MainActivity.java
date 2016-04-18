package com.ilyasavin.yandexartists;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.components.ArtistsController;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.views.MaterialDrawer;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    private ArtistsController mArtistController;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.card_recycler_view)
    RecyclerView mArtistsView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mArtistController = new ArtistsController();
        initViewElements();
        APIManager.getApiService().getData(callback);

    }

    private void initViewElements() {

        setSupportActionBar(toolbar);
        MaterialDrawer materialDrawer = new MaterialDrawer();
        materialDrawer.initDrawer(toolbar, this);
        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mArtistsView.setLayoutManager(layoutManager);
        mArtistsView.setOnScrollListener(onScrollListener);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public Callback<List<Artist>> callback = new Callback<List<Artist>>() {

        @Override
        public void success(List<Artist> artists, Response response2) {

            mArtistController.setArtistsList(artists);
            ArtistsRVAdapter mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this, mArtistController.getSortedArtistsList());
            mArtistsView.setAdapter(mArtistsRVAdapter);
            progressBar.setVisibility(View.GONE);

        }

        @Override
        public void failure(RetrofitError error) {

            Toast.makeText(MainActivity.this, "Response error", Toast.LENGTH_SHORT).show();

        }
    };

    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        boolean hideToolBar = false;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (hideToolBar) {
                toolbar.setVisibility(View.GONE);
            } else {
                toolbar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 10) {
                hideToolBar = true;

            } else if (dy < -5) {
                hideToolBar = false;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_manu, menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);}
    }
