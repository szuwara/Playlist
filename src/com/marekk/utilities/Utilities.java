package com.marekk.utilities;

import com.marekk.music.Playlist;
import com.marekk.music.Song;

import java.util.List;

public class Utilities {


    public static void loopThroughList(List<Song> libraryOfSongs) {
        String[] songAlbumAndArtist;
        int index = 0;
        for (Song song : libraryOfSongs) {
            songAlbumAndArtist = Playlist.getAlbumNameAndArtistNameBySong(song);
            assert songAlbumAndArtist != null;
            System.out.println(index + 1 + ". '" + song.getSongTitle() + "' by " + songAlbumAndArtist[1] +
                    " (" + songAlbumAndArtist[0] +
                    ")");
            index++;
        }
    }

    public static void printBorderLines() {
        System.out.println("-------------------------------");
    }

    public static void printWelcomeBanner() {
        String welcomeText = "=>> PLAYLIST APPLICATION <<=";
        printLinesAboveOrUnderText(welcomeText);
        System.out.println(welcomeText);
        printLinesAboveOrUnderText(welcomeText);
    }

    private static void printLinesAboveOrUnderText(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
    }
}
