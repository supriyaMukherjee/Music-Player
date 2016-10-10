package com.example.supriya.card_view;

/**
 * Created by Supriya on 25-08-2016.
 */
public class Album {
    private String id;
    private String fullpath;
    private String name;
    private int numOfSongs;
    private String thumbnail;

    public Album() {
    }
    public Album(String name,String id,int numOfSongs,String thumbnail) {
        this.name = name;
        this.fullpath=fullpath;
        this.id=id;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public Album(String id,String name,String fullpath) {
        this.name = name;
        this.fullpath=fullpath;
        this.id=id;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
