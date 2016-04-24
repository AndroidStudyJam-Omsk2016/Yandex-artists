package com.ilyasavin.yandexartists.fragments;

import android.view.View;

import com.ilyasavin.yandexartists.adapters.GridRVAdapter;
import com.ilyasavin.yandexartists.db.ArtistRealm;

import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * The fragment for displaying grid of favorite artists.
 */
public class FavoritesFragment extends BaseFragment {

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initFragmentData() {

        RealmResults<ArtistRealm> realmQuery = realm.where(ArtistRealm.class)
                .findAll();

        RealmList<ArtistRealm> artistsList = new RealmList<ArtistRealm>();

        artistsList.addAll(realmQuery.subList(0, realmQuery.size()));

        GridRVAdapter gridAdapter = new GridRVAdapter(getActivity(), artistsList);

        if(artistsList.size()>0)
            noFavoritesLayout.setVisibility(View.GONE);
        else noFavoritesLayout.setVisibility(View.VISIBLE);

        recyclerView.setAdapter(gridAdapter);

    }

}
