package com.ilyasavin.yandexartists.models;

import io.realm.RealmObject;

/**
 * Created by ilyas on 4/19/2016.
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
