package com.ilyasavin.yandexartists;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.api.APIManager;
import com.ilyasavin.yandexartists.components.ArtistsController;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.views.MaterialDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.card_recycler_view)
    RecyclerView mArtistsView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ArtistsController mArtistController;
    private SearchView mSearchView;
    private MenuItem mSearchMenuItem;
    private SearchView.OnQueryTextListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mArtistController = new ArtistsController();
        initViewElements();
        APIManager.getApiService().getData(callback);

        mListener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                progressBar.setVisibility(View.VISIBLE);
                mArtistsView.setVisibility(View.GONE);

                APIManager.getApiService().getData(callback);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!mSearchView.hasFocus())
                    mSearchMenuItem.setVisible(false);

                ArrayList<Artist> tempSearchList = new ArrayList<>();
                for (int  i = 0 ; i < mArtistController.getArtistsList().size() ; i++){
                    if(mArtistController.getArtistsList().get(i).getName().matches("(?i)("+newText+").*")){
                        tempSearchList.add(mArtistController.getArtistsList().get(i));
                    }

                    ArtistsRVAdapter mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this, tempSearchList);
                    mArtistsView.setAdapter(mArtistsRVAdapter);
                    progressBar.setVisibility(View.GONE);
                    mArtistsView.setVisibility(View.VISIBLE);
                }



                return false;
            }
        };

    }

    private void initViewElements() {

        setSupportActionBar(toolbar);
        MaterialDrawer materialDrawer = new MaterialDrawer();
        materialDrawer.initDrawer(toolbar, this);
        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        mArtistsView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public Callback<List<Artist>> callback = new Callback<List<Artist>>() {

        @Override
        public void success(List<Artist> artists, Response response2) {

            showProgressAndUpdateData(artists);

        }

        @Override
        public void failure(RetrofitError error) {

            Toast.makeText(MainActivity.this, "Response error", Toast.LENGTH_SHORT).show();

        }
    };

    private void showProgressAndUpdateData(List<Artist> artists) {
        mArtistController.setArtistsList(artists);
        ArtistsRVAdapter mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this,
                mArtistController.getSortedArtistsList());
        mArtistsView.setAdapter(mArtistsRVAdapter);

        progressBar.setVisibility(View.GONE);
        mArtistsView.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_manu, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mSearchMenuItem.getActionView();
        mSearchView.setIconified(false);
        mSearchView.onActionViewCollapsed();
        mSearchView.setOnQueryTextListener(mListener);


        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);}
    }
