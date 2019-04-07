package music;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class AlbumTest {
    private Song newSong;
    private List<Song> albumOfSongs;


    @Before
    public void setUp() {
        newSong = new Song("Random Title", 200);
        albumOfSongs = new ArrayList<>();
        System.out.println("Set up method executed");
    }

    @Test
    public void addNewSongToAlbum() {
        albumOfSongs.add(newSong);
        assertSame(newSong, albumOfSongs.get(0));
        System.out.println("method test 'addNewSongToAlbum' completed\n***********************************");
    }

    @Test
    public void addSongToAlbum() {
        albumOfSongs.add(newSong);
        assertSame(newSong, albumOfSongs.get(0));
        System.out.println("method test 'addSongToAlbum' completed\n***********************************");
    }

    @Test
    public void isSongExistInAlbum() {
        albumOfSongs.add(newSong);
        for (Song song :
                albumOfSongs) {

            assertSame(newSong, song);
        }
        System.out.println("method test 'isSongExistInAlbum' completed\n***********************************");
    }
}