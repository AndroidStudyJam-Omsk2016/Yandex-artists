package com.ilyasavin.yandexartists.components;

import com.ilyasavin.yandexartists.models.Artist;

import java.util.Comparator;

/**
 * A custom comporator for {@link Artist} objects.
 */

public class ArtistComporator implements Comparator<Artist> {

    public int compare(Artist artistItem1, Artist artistItem2) {
        String obj1 = artistItem1.getName();
        String obj2 = artistItem2.getName();

        if (obj1 == obj2) {
            return 0;
        }
        if (obj1 == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        return obj1.compareTo(obj2);
    }
}
