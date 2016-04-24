package com.ilyasavin.yandexartists.components;

import com.ilyasavin.yandexartists.models.Artist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that holds collection of Artist models.
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


    }

