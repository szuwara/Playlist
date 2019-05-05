package com.marekk.music;

import com.marekk.utilities.DataSource;
import com.marekk.utilities.Utilities;

import java.util.LinkedList;
import java.util.List;


public class Playlist {

    private static final boolean OPEN_CONNECTION = DataSource.openConnection();
    private static List<Song> libraryOfSongs = DataSource.setSongsFromDB();
    private static List<Album> libraryOfAlbums = DataSource.setAlbumsFromDB();
    private static List<Artist> libraryOfArtists = DataSource.setArtistsFromDB();
    private String playlistName;
    private LinkedList<Song> playlistOfSongs;


    Playlist() {
        this.playlistOfSongs = new LinkedList<>();
    }

    public static String[] getAlbumNameAndArtistNameBySong(Song song) {
        String[] songAlbumAndArtist = new String[2];
        String albumName;
        String artistName;
        for (Song foundSong : libraryOfSongs)
            if (foundSong.equals(song)) {
                for (Album foundAlbum : libraryOfAlbums) {
                    if (song.getAlbumID() == foundAlbum.getAlbumID()) {
                        albumName = foundAlbum.getAlbumName();
                        songAlbumAndArtist[0] = albumName;
                        for (Artist foundArtist : libraryOfArtists) {
                            if (foundArtist.getArtistID() == foundAlbum.getArtistID()) {
                                artistName = foundArtist.getArtistName();
                                songAlbumAndArtist[1] = artistName;
                            }
                        }
                        return songAlbumAndArtist;
                    }
                }
            }
        return null;
    }

    public String getAlbumNameAndArtistNameBySong(String songTitle) {
        String albumName;
        for (Song foundSong : libraryOfSongs)
            if (foundSong.getSongTitle().equals(songTitle)) {
                for (Album foundAlbum : libraryOfAlbums) {
                    if (foundSong.getAlbumID() == foundAlbum.getAlbumID()) {
                        albumName = foundAlbum.getAlbumName();
                        return albumName;
                    }
                }
            }
        return null;
    }

    boolean addSongToPlaylist(String songTitle) {
        Song songInLibrary = findSongInLibrary(songTitle);
        if (libraryOfSongs.contains(songInLibrary)) {
            playlistOfSongs.add(songInLibrary);
            return true;
        } else {
            return false;
        }
    }

    boolean isSongAlreadyInPlaylist(String songTitle) {
        Song songInLibrary = findSongInLibrary(songTitle);
        return playlistOfSongs.contains(songInLibrary);
    }

    private Song findSongInLibrary(String songTitle) {
        for (Song foundSong : libraryOfSongs) {
            if (foundSong.getSongTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
    }

    Song findSongInPlaylist(String songTitle) {
        for (Song foundSong : playlistOfSongs) {
            if (foundSong.getSongTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
    }

    void setLibraryOfSongs() {
        for (Album album : libraryOfAlbums) {
            /*song.setAlbumName(album.getAlbumName());
                //song.setArtistID(album.getArtistID());*/
            libraryOfSongs.addAll(album.getAlbumSongs());
        }
    }

    void printSongsInPlaylist() {
        if (playlistOfSongs.isEmpty()) {
            System.out.println("Playlist is empty, please add some tracks to play.");
        } else {
            System.out.println("Playlist: ");
            Utilities.loopThroughList(playlistOfSongs);
        }
    }

    void printAllSongsInLibrary() {
        if (libraryOfSongs.isEmpty()) {
            System.out.println("Your library is empty, please add some tracks to library.");
        } else {
            System.out.println("All songs in library: ");
            Utilities.loopThroughList(libraryOfSongs);
        }
    }

    LinkedList<Song> getPlaylistOfSongs() {
        return playlistOfSongs;
    }

    List<Album> getLibraryOfAlbums() {
        return libraryOfAlbums;
    }

    List<Song> getLibraryOfSongs() {
        return libraryOfSongs;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
