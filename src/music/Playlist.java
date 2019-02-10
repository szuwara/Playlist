package music;

import java.util.ArrayList;
import java.util.LinkedList;

public class Playlist {

    private LinkedList<Song> playlistSongs;
    private ArrayList<Album> libraryOfAlbums;
    private ArrayList<Song> libraryOfSongs;

    Playlist() {
        this.playlistSongs = new LinkedList<>();
        this.libraryOfAlbums = new ArrayList<>();
        this.libraryOfSongs = new ArrayList<>();
    }


    boolean addSongToPlaylist(Song song, Album album) {
        String songTitle = song.getTitle();
        if (album.isSongExistInAlbum(songTitle)) {
            playlistSongs.add(song);
            return true;
        } else {
            System.out.println("Song '" + songTitle + "' not found in album '" + album.getAlbumName() + "'");
            return false;
        }
    }

    Song findSongInPlaylist(String songTitle) {
        for (Song foundSong : playlistSongs) {
            if (foundSong.getTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
    }

    /*Album findAlbumByName (String albumName){
        for (Album album: libraryOfAlbums) {
            if (album.getAlbumName().equals(albumName)){
                return album;
            }
        }
        return null;
    }*/


    void printSongsInPlaylist() {
        Utilities.printBorderLines();
        System.out.println("Playlist: ");
        Utilities.loopInList(playlistSongs);
        Utilities.printBorderLines();
    }

    void printAllSongsInLibrary() {
        int index = 0;
        Utilities.printBorderLines();
        System.out.println("All songs in library: ");
        for (Album album : libraryOfAlbums) {
            for (Song song : album.getAlbumSongs()) {
                System.out.println(index + 1 + ". " + song.getTitle() + " by " + album.getArtistName());
                index++;
            }
        }
        Utilities.printBorderLines();
    }


    LinkedList<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    ArrayList<Album> getLibraryOfAlbums() {
        return libraryOfAlbums;
    }

    ArrayList<Song> getLibraryOfSongs() {
        return libraryOfSongs;
    }

    void setLibraryOfSongs() {
        for (Album album : libraryOfAlbums) {
            libraryOfSongs.addAll(album.getAlbumSongs());
        }
    }
}
