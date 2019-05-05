package com.marekk.music;

public class TempMain {

    private static Playlist playlist = new Playlist();

    public static void main(String[] args) {

        String albumName = playlist.getAlbumNameAndArtistNameBySong("I Can't Quit You Baby");
        System.out.println(albumName);
    }
}
