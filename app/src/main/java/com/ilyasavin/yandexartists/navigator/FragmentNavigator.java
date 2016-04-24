package com.ilyasavin.yandexartists.navigator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ilyasavin.yandexartists.R;


/**
 * Fragment navigation.
 */
public class FragmentNavigator {

    public static void showFavoriteFragment(Activity activity, Fragment fragment) {

        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.favorites_frame);
        frameLayout.setVisibility(View.VISIBLE);

        FrameLayout noFavoritesLayout = (FrameLayout) activity.findViewById(R.id.no_favorites);
        noFavoritesLayout.setVisibility(View.VISIBLE);

        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.favorites_frame, fragment);
        ft.commit();
    }

    public static void removeFavoriteFragment(Activity activity, Fragment fragment) {

        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.favorites_frame);
        frameLayout.setVisibility(View.GONE);

        FrameLayout noFavoritesLayout = (FrameLayout) activity.findViewById(R.id.no_favorites);
        noFavoritesLayout.setVisibility(View.GONE);


        if (activity.getFragmentManager().findFragmentById(R.id.favorites_frame) != null)
            activity.getFragmentManager().
                    beginTransaction().remove(activity.getFragmentManager().findFragmentById(R.id.favorites_frame)).commit();
    }
}
