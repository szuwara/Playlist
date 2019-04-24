package music;

import java.sql.*;
import java.util.ArrayList;

class Album {

    private String albumName;
    private String artistName;
    private ArrayList<Song> albumSongs;
    public static final String SONGS_TABLE = "songs";

    void setSongsFromDB() {
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
                albumSongs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    Album(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumSongs = new ArrayList<>();
    }

    void addNewSongToAlbum(String songTitle) {
        Song song = new Song();
        song.setTitle(songTitle);
        albumSongs.add(song);
    }

    void addSongToAlbum(Song song) {
        song.setAlbumName(albumName);
        //song.setArtistName(artistName);
        albumSongs.add(song);
    }

    private Song findSongInAlbum(String songTitle) {
        for (Song foundSong : albumSongs) {
            if (foundSong.getTitle().equals(songTitle)) {
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
        System.out.println("Artist [" + artistName.toUpperCase() + "]");
        System.out.println("Album [" + albumName + "]");
        Utilities.loopThroughList(albumSongs);
        Utilities.printBorderLines();
    }

    String getAlbumName() {
        return albumName;
    }

    String getArtistName() {
        return artistName;
    }

    ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }
}
