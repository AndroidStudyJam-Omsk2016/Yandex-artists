package com.ilyasavin.yandexartists.components;

import com.ilyasavin.yandexartists.db.ArtistRealm;

import io.realm.RealmList;

/**
 * A class that holds collection of RealmArtist models.
 */
public class RealmArtistController {

    private RealmList<ArtistRealm> mArtistsList = new RealmList<>();

    public RealmList<ArtistRealm> getArtistsList() {
        return (RealmList<ArtistRealm>) mArtistsList;
    }

    public void setArtistsList(RealmList<ArtistRealm> mArtistsList) {
        this.mArtistsList = mArtistsList;
    }

}
