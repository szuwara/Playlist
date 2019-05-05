package com.marekk.music;


public class Song {

    private int songID;
    private int songTrack;
    private String songTitle;
    private int albumID;

    public Song() {
    }


    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getSongTrack() {
        return songTrack;
    }

    public void setSongTrack(int songTrack) {
        this.songTrack = songTrack;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

}
