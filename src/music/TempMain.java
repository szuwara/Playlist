package music;

public class TempMain {

    private static Playlist playlist = new Playlist();

    public static void main(String[] args) {

        Playlist.setSongsFromDB();
        System.out.println(playlist.getLibraryOfSongs().size());
    }
}
