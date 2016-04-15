package com.ilyasavin.yandexartists.adapters;

/**
 * Created by ilyas on 4/10/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilyasavin.yandexartists.MainActivity;
import com.ilyasavin.yandexartists.R;
import com.ilyasavin.yandexartists.models.Artist;
import com.ilyasavin.yandexartists.navigator.ActivityNavigator;
import com.squareup.picasso.Picasso;


import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        return new ViewHolder(view,viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.artistName.setText(artistsList.get(i).getName());
        viewHolder.genresNames.setText(artistsList.get(i).getGenres().get(0));
        viewHolder.amountSongs.setText(artistsList.get(i).getTracks()+ " Tracks");
        Picasso.with(context).load(artistsList.get(i).getCover().getSmall()).error( android.R.drawable.ic_delete )
                .placeholder( R.drawable.image_progress).into(viewHolder.artistImage);

    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.tv_name)
        TextView artistName;
        @Bind(R.id.tv_genres)
        TextView genresNames;
        @Bind(R.id.tv_amount)
        TextView amountSongs;
        @Bind(R.id.img_android)
        ImageView artistImage;

        protected Context context;

        public ViewHolder(View view,Context context) {
            super(view);

            view.setOnClickListener(this);
            ButterKnife.bind(this, view);

            this.context = context;
        }

        @Override
        public void onClick(View v) {
            Artist temp = artistsList.get(getPosition());
            ActivityNavigator.startArtistActivity((Activity)context,temp,artistImage);
        }
    }
}