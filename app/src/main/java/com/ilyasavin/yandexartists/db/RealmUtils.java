package com.ilyasavin.yandexartists.db;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Contains methods for realm database
 */
public class RealmUtils {

    public static boolean checkIfExists(Realm realm, int id) {

        RealmQuery<ArtistRealm> query = realm.where(ArtistRealm.class)
                .equalTo("id", id);

        return query.count() == 0 ? false : true;
    }

}
