package com.ilyasavin.yandexartists.components;

import com.ilyasavin.yandexartists.models.Artist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilyas on 4/17/2016.
 */

public class ArtistsController {

    private List<Artist> mArtistsList = new ArrayList<>();

    public ArrayList<Artist> getArtistsList() {
        return (ArrayList) mArtistsList;
    }

    public void setArtistsList(List<Artist> mArtistsList) {
        this.mArtistsList = mArtistsList;
    }

    public ArrayList<Artist> getSortedArtistsList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(mArtistsList);
        Collections.sort(arrayList, new ArtistComporator());
        return arrayList ;
    }

    public ArrayList<Artist> getSimpleArtistsList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(mArtistsList);
        //Collections.sort(arrayList, new ArtistComporator());
        return arrayList ;
    }
}
