package com.ilyasavin.yandexartists;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ilyasavin.yandexartists.models.Artist;
import com.squareup.picasso.Picasso;

public class ArtistInfoActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Artist mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        initViewElements();
    }

    private void initViewElements() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mImageView = (ImageView)findViewById(R.id.artistImage);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName("ANIM");
        }

        mArtist = getIntent().getParcelableExtra("Artist");

        Picasso.with(this).load(mArtist.getCover().getSmall()).into(mImageView);

        toolbar.setTitle(mArtist.getName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
