package com.ilyasavin.yandexartists;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilyasavin.yandexartists.models.Artist;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistInfoActivity extends BaseActivity {

    @Bind(R.id.artistImage)
    ImageView mImageView;
    @Bind(R.id.tv_descr)
    TextView mDescriptionText;
    @Bind(R.id.tv_genres)
    TextView mGenresText;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        initViewElements();
    }

    private void initViewElements() {

        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName("ANIM");
        }

        Artist mArtist = getIntent().getParcelableExtra("Artist");

        Picasso.with(this).load(mArtist.getCover().getBig()).into(mImageView);

        toolbar.setTitle(mArtist.getName());
        mGenresText.setText(mArtist.getGenres().get(0));
        mDescriptionText.setText(mArtist.getDescription());

        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
