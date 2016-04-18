package com.ilyasavin.yandexartists.navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.ilyasavin.yandexartists.ArtistInfoActivity;
import com.ilyasavin.yandexartists.components.Constants;


/**
 * Created by ilyas on 4/11/2016.
 */
public class ActivityNavigator {

    public static void startArtistActivity(Activity context, Parcelable parcelable, View imageView) {

        Intent intent = new Intent(context, ArtistInfoActivity.class);

        intent.putExtra(Constants.ARTIST_EXTRA, parcelable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(Constants.ARTIST_TRANSITION);
            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(context, pair1);
            context.startActivity(intent, options.toBundle());
        } else
            context.startActivity(intent);
    }
}

