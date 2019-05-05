package com.marekk.utilities;

import com.marekk.music.Album;
import com.marekk.music.Artist;
import com.marekk.music.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static Connection connection;

    private static final String DATABASE_NAME = "music.db";
    private static final String CONNECTION_URL = "jdbc:sqlite:C:\\Users\\kozlo\\JAVA Projects\\Playlist\\resources\\" + DATABASE_NAME;

    private static final String SONGS_TABLE = "songs";
    private static final String ALBUMS_TABLE = "albums";
    private static final String ARTISTS_TABLE = "artists";

    public static boolean openConnection() {

        try {
            connection = DriverManager.getConnection(CONNECTION_URL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to database failed. " + e.getMessage());
            return false;
        }
    }

    public static void closeConnection() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Disconnection to database failed. " + e.getMessage());
            }
        }
    }

    public static List<Song> setSongsFromDB() {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SONGS_TABLE)) {

            List<Song> libraryOfSongs = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
                song.setSongID(resultSet.getInt("_id"));
                song.setSongTrack(resultSet.getInt("track"));
                song.setSongTitle(resultSet.getString("title"));
                song.setAlbumID(resultSet.getInt("album"));
                libraryOfSongs.add(song);
            }
            return libraryOfSongs;

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Album> setAlbumsFromDB() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + ALBUMS_TABLE)) {

            List<Album> libraryOfAlbums = new ArrayList<>();
            while ((resultSet.next())) {
                Album album = new Album();
                album.setAlbumID(resultSet.getInt("_id"));
                album.setAlbumName(resultSet.getString("name"));
                album.setArtistID(resultSet.getInt("artist"));
                libraryOfAlbums.add(album);
            }
            return libraryOfAlbums;

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Artist> setArtistsFromDB() {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + ARTISTS_TABLE)) {

            List<Artist> libraryOfArtists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setArtistID(resultSet.getInt("_id"));
                artist.setArtistName(resultSet.getString("name"));
                libraryOfArtists.add(artist);
            }
            return libraryOfArtists;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
