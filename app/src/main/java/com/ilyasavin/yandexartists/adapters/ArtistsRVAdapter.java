package com.ilyasavin.yandexartists.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilyasavin.yandexartists.R;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.navigator.ActivityNavigator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An adapter for the list of artists
 */
public class ArtistsRVAdapter extends RecyclerView.Adapter<ArtistsRVAdapter.ViewHolder> {

    private ArrayList<Artist> artistsList;
    private Context context;

    public ArtistsRVAdapter(Context context, ArrayList<Artist> artistsList) {
        this.context = context;
        this.artistsList = artistsList;
    }

    @Override
    public ArtistsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artist, viewGroup, false);
        return new ViewHolder(view, viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.artistName.setText(artistsList.get(i).getName());
        viewHolder.genresNames.setText(android.text.TextUtils.join(",", artistsList.get(i).getGenres()));
        viewHolder.amountAlbums.setText(artistsList.get(i).getAlbums() + " "+ context.getString(R.string.albums));
        viewHolder.amountSongs.setText(artistsList.get(i).getTracks() + " "+ context.getString(R.string.tracks));
        Picasso.with(context).load(artistsList.get(i).getCover().getSmall()).error(R.drawable.ic_music_note_white_48dp)
                .placeholder(R.drawable.image_progress).into(viewHolder.artistImage);

    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_name)
        TextView artistName;
        @Bind(R.id.tv_genres)
        TextView genresNames;
        @Bind(R.id.tv_tracks)
        TextView amountSongs;
        @Bind(R.id.tv_albums)
        TextView amountAlbums;
        @Bind(R.id.img_android)
        ImageView artistImage;

        protected Context context;

        public ViewHolder(View view, Context context) {
            super(view);

            view.setOnClickListener(this);
            ButterKnife.bind(this, view);

            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Artist temp = artistsList.get(getPosition());
            artistImage.setBackground(context.getResources().getDrawable(R.drawable.imageview_gradient));
            ActivityNavigator.startArtistActivity((Activity) context, temp, artistImage);
        }
    }
}