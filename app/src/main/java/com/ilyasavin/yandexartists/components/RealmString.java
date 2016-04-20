package com.ilyasavin.yandexartists.components;

import io.realm.RealmObject;

/**
 * Created by ilyas on 4/20/2016.
 */
public class RealmString extends RealmObject {
    String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
