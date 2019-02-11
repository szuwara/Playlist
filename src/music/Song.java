package music;


class Song {

    private String title;
    private String artistName;
    private String albumName;
    private int duration;

    Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.albumName = getAlbumName();
        this.artistName = getArtistName();
    }

    String getTitle() {
        return title;
    }

    int getDuration() {
        return duration;
    }

    String getArtistName() {
        return artistName;
    }

    String getAlbumName() {
        return albumName;
    }

    void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
