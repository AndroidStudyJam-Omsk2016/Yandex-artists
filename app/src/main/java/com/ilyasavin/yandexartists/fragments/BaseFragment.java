package com.ilyasavin.yandexartists.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilyasavin.yandexartists.R;

/**
 * Created by ilyas on 4/20/2016.
 */
public abstract class BaseFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_shapes, container, false);

        initViewElements(view);

        return view;
    }

    protected abstract void initViewElements(View view);
}
