package com.ilyasavin.yandexartists.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;


public class Cover implements Parcelable {

    private String small;
    private String big;

    public Cover() {
    }

    protected Cover(Parcel in) {
        small = in.readString();
        big = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(big);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };


    /**
     * @return The small
     */
    public String getSmall() {
        return small;
    }

    /**
     * @param small The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     * @return The big
     */
    public String getBig() {
        return big;
    }

    /**
     * @param big The big
     */
    public void setBig(String big) {
        this.big = big;
    }


}
