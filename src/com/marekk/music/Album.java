package com.marekk.music;

import com.marekk.utilities.Utilities;

import java.util.ArrayList;

public class Album {

    private int albumID;
    private String albumName;
    private int artistID;
    private ArrayList<Song> albumSongs;


    public Album() {
        this.albumSongs = new ArrayList<>();
    }


    void addNewSongToAlbum(String songTitle) {
        Song song = new Song();
        song.setSongTitle(songTitle);
        albumSongs.add(song);
    }

    void addSongToAlbum(Song song) {
        //song.setAlbumName(albumName);
        //song.setArtistID(artistID);
        albumSongs.add(song);
    }

    private Song findSongInAlbum(String songTitle) {
        for (Song foundSong : albumSongs) {
            if (foundSong.getSongTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
    }

    boolean isSongExistInAlbum(String songTitle) {
        if (!albumSongs.isEmpty()) {
            return findSongInAlbum(songTitle) != null;
        }
        return false;
    }

    void printSongsInAlbum() {
        Utilities.printBorderLines();
        System.out.println("Artist ID [" + getArtistID() + "]");
        System.out.println("Album [" + albumName + "]");
        Utilities.loopThroughList(albumSongs);
        Utilities.printBorderLines();
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public void setAlbumSongs(ArrayList<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }

    ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }
}
