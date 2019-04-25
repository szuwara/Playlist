package music;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;


class Playlist {

    private LinkedList<Song> playlistOfSongs;
    private ArrayList<Album> libraryOfAlbums = new ArrayList<>();
    private static ArrayList<Song> libraryOfSongs = new ArrayList<>();

    public static final String SONGS_TABLE = "songs";

    Playlist() {
        this.playlistOfSongs = new LinkedList<>();
    }

    static ArrayList<Song> setSongsFromDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kozlo\\JAVA Projects\\Playlist\\resources\\music.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SONGS_TABLE);

            while (resultSet.next()) {
                Song song = new Song();
                song.setId(resultSet.getInt("_id"));
                song.setTrack(resultSet.getInt("track"));
                song.setTitle(resultSet.getString("title"));
                song.setAlbumID(resultSet.getInt("album"));
                libraryOfSongs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        return libraryOfSongs;

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
            if (foundSong.getTitle().equals(songTitle)) {
                return foundSong;
            }
        }
        return null;
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
                //song.setArtistName(album.getArtistName());
                libraryOfSongs.add(song);
            }
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

    ArrayList<Album> getLibraryOfAlbums() {
        return libraryOfAlbums;
    }

    ArrayList<Song> getLibraryOfSongs() {
        return libraryOfSongs;
    }
}
