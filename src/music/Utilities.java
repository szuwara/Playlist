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

    static void loopInList(List<Song> listOfSongs) {
        int index = 1;
        for (Song song : listOfSongs) {
            System.out.println(index + ". " + song.getTitle());
            index++;
        }
    }


}
