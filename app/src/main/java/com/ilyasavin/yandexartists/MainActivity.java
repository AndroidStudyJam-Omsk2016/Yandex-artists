package com.ilyasavin.yandexartists;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ilyasavin.yandexartists.adapters.ArtistsRVAdapter;
import com.ilyasavin.yandexartists.components.ArtistsController;
import com.ilyasavin.yandexartists.components.Constants;
import com.ilyasavin.yandexartists.data.DataService;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.views.MaterialDrawer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener,SwipeRefreshLayout.OnRefreshListener {

    private ArtistsController mArtistController;
    private SearchView mSearchView;
    private MenuItem mSearchMenuItem;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.card_recycler_view)
    RecyclerView mArtistsView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArtistController = new ArtistsController();

        getData();

        setContentView(R.layout.activity_main);

        initBroadcastReceivers();
        initViewElements();

    }

    private void initViewElements() {

        setSupportActionBar(toolbar);
        MaterialDrawer materialDrawer = new MaterialDrawer();
        materialDrawer.initDrawer(toolbar, this);
        mArtistsView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        mArtistsView.setLayoutManager(layoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    BroadcastReceiver mDataDownloadReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {

                case Constants.DATA_DOWNLOADED:
                    swipeRefreshLayout.setRefreshing(false);
                    getData();
                    break;
            }
        }
    };

    private void initBroadcastReceivers() {

        IntentFilter filter= new IntentFilter();
        filter.addAction(Constants.DATA_DOWNLOADED);
        registerReceiver(mDataDownloadReceiver,filter);

    }

    private void getData(){
        DataService.init().getDataFromServer(new DataService.onArtistsResult() {
            public void onArtistsResult(List<Artist> artists) {
                showProgressAndUpdateData(artists);
            }
        });
    }

    private void showProgressAndUpdateData(List<Artist> artists) {

        mArtistController.setArtistsList(artists);
        ArtistsRVAdapter mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this,
                mArtistController.getArtistsList());
        mArtistsView.setAdapter(mArtistsRVAdapter);

        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        mArtistsView.setVisibility(View.VISIBLE);
    }

    private void searchArtists(String newText) {
        ArrayList<Artist> tempSearchList = new ArrayList<>();
        for (int  i = 0 ; i < mArtistController.getArtistsList().size() ; i++){
            if(mArtistController.getArtistsList().get(i).getName().matches("(?i)("+newText+").*")){
                tempSearchList.add(mArtistController.getArtistsList().get(i));
            }

            ArtistsRVAdapter mArtistsRVAdapter = new ArtistsRVAdapter(MainActivity.this,
                   tempSearchList);
            mArtistsView.setAdapter(mArtistsRVAdapter);

            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            mArtistsView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_manu, menu);

        initSearchItems(menu);

        return true;


    }

    private void initSearchItems(Menu menu) {
        mSearchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mSearchMenuItem.getActionView();
        mSearchView.setIconified(false);
        mSearchView.onActionViewCollapsed();
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_filter1:
                showProgressAndUpdateData(mArtistController.getSortedArtistsList());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);}

    @Override
    public boolean onQueryTextSubmit(String query) {

        getData();
        mSearchMenuItem.setVisible(true);
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!mSearchView.hasFocus()){
            mSearchMenuItem.setVisible(false);
        }

        searchArtists(newText);

        return false;

    }

    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(true);

    }
}
