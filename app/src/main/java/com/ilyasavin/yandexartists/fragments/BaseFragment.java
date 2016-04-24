package com.ilyasavin.yandexartists.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.ilyasavin.yandexartists.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * The abstract fragment for displaying  artists.
 */
public abstract class BaseFragment extends Fragment {


    protected RecyclerView recyclerView;
    protected FrameLayout noFavoritesLayout;

    protected Realm realm = Realm.getDefaultInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grid_shapes, container, false);

        initViewElements(view);


        initFragmentData();

        return view;
    }

    protected void initViewElements(View view){

            GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);

            noFavoritesLayout = (FrameLayout)getActivity().findViewById(R.id.no_favorites);
            recyclerView = (RecyclerView) view.findViewById(R.id.grid_artists);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(mGridLayoutManager);
        }

    protected abstract void initFragmentData();
}
