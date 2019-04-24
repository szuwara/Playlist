package music;


class Song {

    private int id;
    private int track;
    private String title;
    private int albumID;
    //private String artistName;
    private String albumName;

    public Song() {
    }

    public Song(int id, int track, String title) {
        this.id = id;
        this.track = track;
        this.title = title;
        this.albumName = getAlbumName();
        //this.artistName = getArtistName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    /*String getArtistName() {
        return artistName;
    }*/

    String getAlbumName() {
        return albumName;
    }

    /*void setArtistName(String artistName) {
        this.artistName = artistName;
    }*/

    void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
