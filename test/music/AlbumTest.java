package music;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertSame;

@RunWith(Parameterized.class)
public class AlbumTest {
    private Song newSong;
    private Song newSong2;
    private Song newSong3;
    private Song newSong4;
    private List<Song> albumOfSongs;


    @Before
    public void setUp() {
        newSong = new Song();
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

    @Parameterized.Parameters
    public static Collection testConditions() {
        return Arrays.asList(new Object[][]{
                {},
                {},
                {},
        });
    }
}