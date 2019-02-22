package music;

import java.util.List;

class Utilities {

    static void loopThroughList(List<Song> libraryOfSongs) {
        int index = 0;
        for (Song song : libraryOfSongs) {
            System.out.println(index + 1 + ". '" + song.getTitle() + "' by " + song.getArtistName() + " (" + song.getAlbumName() + ")");
            index++;
        }
    }

    static void printBorderLines() {
        System.out.println("-------------------------------");
    }

    static void printWelcomeBanner() {
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
