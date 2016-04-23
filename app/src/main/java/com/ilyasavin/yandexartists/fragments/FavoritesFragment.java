package com.ilyasavin.yandexartists.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilyasavin.yandexartists.R;
import com.ilyasavin.yandexartists.adapters.GridRVAdapter;
import com.ilyasavin.yandexartists.db.ArtistRealm;
import com.ilyasavin.yandexartists.models.Artist;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends BaseFragment {


    private RecyclerView recyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_shapes, container, false);

        initViewElements(view);

        return view;
    }

    protected void initViewElements(View view) {

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView = (RecyclerView) view.findViewById(R.id.grid_artists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mGridLayoutManager);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<ArtistRealm> realmQuery = realm.where(ArtistRealm.class)
                .findAll();

        RealmList<ArtistRealm> artistsList = new RealmList<ArtistRealm>();

        artistsList.addAll(realmQuery.subList(0,realmQuery.size()));

        GridRVAdapter gridAdapter = new GridRVAdapter(getActivity(),artistsList);

        recyclerView.setAdapter(gridAdapter);

    }

}
