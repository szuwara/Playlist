package music;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlbumTest {
    private Song song;
    private List<Song> albumOfSongs;

    @Before
    public void setUp() {
        song = new Song("Random Title", 200);
        albumOfSongs = new ArrayList<>();
        System.out.println("Set up method executed");
    }

    @Test
    public void addNewSongToAlbum() {
        assertTrue(albumOfSongs.isEmpty());
        albumOfSongs.add(song);
        assertFalse(albumOfSongs.isEmpty());
        System.out.println("method test 'addNewSongToAlbum' completed");
    }

    @Test
    public void addSongToAlbum() {
        fail("Not implemented yet");
    }

    @Test
    public void isSongExistInAlbum() {
        fail("Not implemented yet");
    }

    @Test
    public void printSongsInAlbum() {
        fail("Not implemented yet");
    }

    @Test
    public void getAlbumName() {
        fail("Not implemented yet");
    }

    @Test
    public void getArtistName() {
        fail("Not implemented yet");
    }

    @Test
    public void getAlbumSongs() {
        fail("Not implemented yet");
    }
}