package com.ilyasavin.yandexartists.db;

import io.realm.RealmObject;

/**
 * An artist cover model is used in realm database.
 */
public class CoverRealm extends RealmObject {

    private String small;
    private String big;

    public CoverRealm() {
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }
}
