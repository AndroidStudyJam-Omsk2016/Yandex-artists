package com.ilyasavin.yandexartists.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class Artist implements Parcelable {

    private String description;
    private Cover cover;
    private Integer id;
    private String name;
    private List<String> genres = new ArrayList<String>();
    private Integer tracks;
    private Integer albums;
    private String link;
    protected Artist(Parcel in) {

        description = in.readString();
        cover = (Cover) in.readValue(Cover.class.getClassLoader());
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        if (in.readByte() == 0x01) {
            genres = new ArrayList<String>();
            in.readList(genres, String.class.getClassLoader());
        } else {
            genres = null;
        }
        tracks = in.readByte() == 0x00 ? null : in.readInt();
        albums = in.readByte() == 0x00 ? null : in.readInt();
        link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeValue(cover);
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (genres == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(genres);
        }
        if (tracks == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(tracks);
        }
        if (albums == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(albums);
        }
        dest.writeString(link);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<String> getGenres() {
        return genres;
    }


    public void setGenres(List<String> genres) {
        this.genres = genres;
    }


    public Integer getTracks() {
        return tracks;
    }


    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }


    public Integer getAlbums() {
        return albums;
    }


    public void setAlbums(Integer albums) {
        this.albums = albums;
    }


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Cover getCover() {
        return cover;
    }


    public void setCover(Cover cover) {
        this.cover = cover;
    }


}
