package music;

import java.util.ArrayList;

class Album {

    private String albumName;
    private String artistName;
    private ArrayList<Song> albumSongs;

    Album(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumSongs = new ArrayList<>();
    }

    void addNewSongToAlbum(String songTitle, int songDuration) {
        Song song = new Song(songTitle, songDuration);
        albumSongs.add(song);
    }

    void addSongToAlbum(Song song) {
        song.setAlbumName(albumName);
        song.setArtistName(artistName);
        albumSongs.add(song);
    }

    Song findSongInAlbum(String songTitle) {
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
