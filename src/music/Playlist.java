package music;

import java.util.ArrayList;
import java.util.LinkedList;

class Playlist {

    private LinkedList<Song> playlistOfSongs;
    private ArrayList<Album> libraryOfAlbums;
    private ArrayList<Song> libraryOfSongs;

    Playlist() {
        this.playlistOfSongs = new LinkedList<>();
        this.libraryOfAlbums = new ArrayList<>();
        this.libraryOfSongs = new ArrayList<>();
    }

    boolean addSongToPlaylist(Song song) {
        String songTitle = song.getTitle();
        if (libraryOfSongs.contains(song)) {
            playlistOfSongs.add(song);
            return true;
        } else {
            System.out.println("Song '" + songTitle + "' not found in library!");
            return false;
        }
    }

    Song findSongInPlaylist(String songTitle) {
        for (Song foundSong : playlistOfSongs) {
            if (foundSong.getTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
    }

    void setLibraryOfSongs() {
        for (Album album : libraryOfAlbums) {
            for (Song song : album.getAlbumSongs()) {
                song.setAlbumName(album.getAlbumName());
                song.setArtistName(album.getArtistName());
                libraryOfSongs.add(song);
            }
        }
    }

    void printSongsInPlaylist() {
        Utilities.printBorderLines();
        if (playlistOfSongs.isEmpty()) {
            System.out.println("Playlist is empty, please add some tracks to play.");
        } else {
            System.out.println("Playlist: ");
            Utilities.loopThroughList(playlistOfSongs);
        }
        Utilities.printBorderLines();
    }

    void printAllSongsInLibrary() {
        Utilities.printBorderLines();
        if (libraryOfSongs.isEmpty()) {
            System.out.println("Your library is empty, please add some tracks to library.");
        } else {
            System.out.println("All songs in library: ");
            Utilities.loopThroughList(libraryOfSongs);
        }
        Utilities.printBorderLines();
    }

    LinkedList<Song> getPlaylistOfSongs() {
        return playlistOfSongs;
    }

    ArrayList<Album> getLibraryOfAlbums() {
        return libraryOfAlbums;
    }

    ArrayList<Song> getLibraryOfSongs() {
        return libraryOfSongs;
    }
}
