package com.ilyasavin.yandexartists;


import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilyasavin.yandexartists.components.Constants;
import com.ilyasavin.yandexartists.db.ArtistRealm;
import com.ilyasavin.yandexartists.db.RealmUtils;
import com.ilyasavin.yandexartists.models.Artist;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * The activity is used to show additional information of artist.
 */
public class ArtistInfoActivity extends BaseActivity {

    @Bind(R.id.artistImage)
    ImageView mImageView;
    @Bind(R.id.tv_descr)
    TextView mDescriptionText;
    @Bind(R.id.tv_genres)
    TextView mGenresText;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private Artist mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);

        mArtist = getIntent().getParcelableExtra(Constants.ARTIST_EXTRA);

        initViewElements();

    }

    private void initViewElements() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageView.setTransitionName(Constants.ARTIST_TRANSITION);
        }

        Picasso.with(this).load(mArtist.getCover().getSmall()).into(mImageView);
        toolbar.setTitle(mArtist.getName());
        mGenresText.setText(android.text.TextUtils.join(",", mArtist.getGenres()));
        mDescriptionText.setText(mArtist.getDescription());

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (RealmUtils.checkIfExists(realm, mArtist.getId()))
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_white_24dp));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addToFavorites(mArtist))
                    Snackbar.make(view, mArtist.getName() + " "+getString(R.string.added_to_favorites), Snackbar.LENGTH_SHORT)
                            .show();
                else
                    Toast.makeText(ArtistInfoActivity.this, " "+getString(R.string.already_in_favorites), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private boolean addToFavorites(Artist mArtist) {

        if (!RealmUtils.checkIfExists(realm, mArtist.getId())) {
            realm.beginTransaction();
            ArtistRealm artistRealm = new ArtistRealm(mArtist.getDescription(),
                    mArtist.getCover().getSmall(),
                    mArtist.getId(),
                    mArtist.getName(),
                    android.text.TextUtils.join(",", mArtist.getGenres()),
                    mArtist.getTracks(),
                    mArtist.getAlbums(),
                    mArtist.getLink()
            );
            realm.copyToRealm(artistRealm);
            realm.commitTransaction();
            return true;
        } else {

            return false;
        }

    }
}
