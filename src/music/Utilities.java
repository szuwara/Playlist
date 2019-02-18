package music;

import java.util.List;

class Utilities {

    static void printBorderLines() {
        System.out.println("-------------------------------");
    }

    static void printLinesAboveOrUnderText(String text) {
        for (int i = 0; i < text.length() + 6; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    static void loopThroughList(List<Song> libraryOfSongs) {
        int index = 0;
        for (Song song : libraryOfSongs) {
            System.out.println(index + 1 + ". '" + song.getTitle() + "' by " + song.getArtistName() + " (" + song.getAlbumName() + ")");
            index++;
        }
    }

    static String capitilizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
