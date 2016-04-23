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
import com.ilyasavin.yandexartists.components.RealmArtistController;
import com.ilyasavin.yandexartists.db.ArtistRealm;
import com.ilyasavin.yandexartists.navigator.ActivityNavigator;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by ilyas on 4/10/2016.
 */
public class GridRVAdapter extends RecyclerView.Adapter<GridRVAdapter.GridRVHolder> {

    private RealmArtistController mArtistController;
    private Context mContext;

    public GridRVAdapter(Context context, RealmList<ArtistRealm> itemList) {
        mArtistController = new RealmArtistController();
        mArtistController.setArtistsList(itemList);
        this.mContext = context;
    }

    @Override
    public GridRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_list_item, null);
        GridRVHolder rcv = new GridRVHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(GridRVHolder holder, int position) {

        holder.artistItemName.setText(mArtistController.getArtistsList().get(position).getName());
        Picasso.with(mContext).load(mArtistController.getArtistsList().get(position).getCover()).error(android.R.drawable.ic_delete)
                .placeholder(R.drawable.image_progress).into(holder.artistItemImage);

    }

    @Override
    public int getItemCount() {

        return this.mArtistController.getArtistsList().size();
    }

    public class GridRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.shape_image_name) TextView artistItemName;
        @Bind(R.id.shape_image_grid) ImageView artistItemImage;

        public GridRVHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            ActivityNavigator.startArtistActivityFromGrid((Activity)mContext,mArtistController.getArtistsList().get(getPosition()),artistItemImage);

        }
}
    }