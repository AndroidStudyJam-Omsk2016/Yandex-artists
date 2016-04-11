package com.ilyasavin.yandexartists.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.ilyasavin.yandexartists.ArtistInfoActivity;
import com.ilyasavin.yandexartists.MainActivity;
import com.ilyasavin.yandexartists.R;

/**
 * Created by ilyas on 4/11/2016.
 */
public class ActivityNavigator {


    public static void startArtistActivity(Activity context, Parcelable parcelable, View imageView){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Intent intent = new Intent(context, ArtistInfoActivity.class);

            intent.putExtra("Artist",parcelable);

            imageView.setTransitionName("ANIM");
            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(context, pair1);
            context.startActivity(intent, options.toBundle());
        }
        else {
            Intent i = new Intent(context, ArtistInfoActivity.class);
            i.putExtra("Artist", parcelable);
            context.startActivity(i);
        }
    }


}
