package music;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Playlist playlist = new Playlist();

    public static void main(String[] args) {
        Song yellow = new Song("Yellow", 180);
        Song parachutes = new Song("Parachutes", 120);
        Song shiver = new Song("Shiver", 160);
        Album parachutesAlbum = new Album("Parachutes", "Coldplay");
        Collections.addAll(parachutesAlbum.getAlbumSongs(), yellow, parachutes, shiver);

        Song sexOnFire = new Song("Sex on Fire", 190);
        Song useSomebody = new Song("Use Somebody", 210);
        Song pyro = new Song("Pyro", 240);
        Song radioactive = new Song("Radioactive", 150);
        Album onlyByTheNightAlbum = new Album("Only By The Night", "Kings of Leon");
        Album comeAroundSundownAlbum = new Album("Come Around Sundown", "Kings of Leon");
        Collections.addAll(onlyByTheNightAlbum.getAlbumSongs(), sexOnFire, useSomebody);
        Collections.addAll(comeAroundSundownAlbum.getAlbumSongs(), pyro, radioactive);

        initializeLibrary(parachutesAlbum, onlyByTheNightAlbum, comeAroundSundownAlbum);

        printPrimaryOptions();
        boolean quitProgram = false;
        while (!quitProgram) {
            try {
                int primaryChoice = input.nextInt();
                input.nextLine();
                userPrimaryChoice(primaryChoice);
                if (primaryChoice == 7) {
                    quitProgram = true;
                } else if (primaryChoice == 6) {
                    int secndaryChoice = input.nextInt();
                    input.nextLine();
                    printSecondaryOptions();
                    userSecondaryChoice(secndaryChoice);
                    if (secndaryChoice == 5) {
                        printPrimaryOptions();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR! ONLY INTEGERS ALLOWED!!!");
                input.next();
            }
        }
    }

    private static void initializeLibrary(Album album1, Album album2, Album album3) {
        Collections.addAll(playlist.getLibraryOfAlbums(), album1, album2, album3);
        playlist.setLibraryOfSongs();
    }

    private static void userPrimaryChoice(int choice) {
        switch (choice) {
            case 1:
                printPrimaryOptions();
                break;
            case 2:
                playlist.printSongsInPlaylist();
                break;
            case 3:
                playlist.printAllSongsInLibrary();
                break;
            case 7:
                input.close();
                break;

            default:
                Utilities.printBorderLines();
                System.out.println("Please type number in range 1-7");
                Utilities.printBorderLines();
        }
    }

    private static void userSecondaryChoice(int choice) { //create features
        switch (choice) {
            case 1: //play current song
                break;
            case 2: //skip next
                break;
            case 3: //previous
                break;
            case 4: //remove from playlist
                break;
            case 5: //replay
                break;
            case 6: //back to main options
            default:
                System.out.println("...");
        }
    }

    private static void printPrimaryOptions() {
        Utilities.printBorderLines();
        System.out.println(">> PLAYLIST APPLICATION CONTROLLER <<" +
                "\nOptions:" +
                "\n1 - >Show all options" +
                "\n2 - >Show all songs in current playlist" +
                "\n3 - >Show all songs in entire library" +
                "\n4 - >Add specific song to playlist" + //create feature
                "\n5 - >Add random songs to playlist" + //create feature
                "\n6 - >>Redirect to options of playlist controller" + //create feature
                "\n7 - >>>Quit Program");
        Utilities.printBorderLines();
    }

    private static void printSecondaryOptions() {
        Utilities.printBorderLines();
        System.out.println("> CONTROLS <" +
                "\n1 - Play" +
                "\n2 - Skip to next song" +
                "\n3 - Skip to previous song" +
                "\n4 - Remove song from playlist" +
                "\n5 - Replay song" +
                "\n6 - Back to main options");
        Utilities.printBorderLines();
    }

}

//TODO zaincjalizowac dobrze while zeby dzialalo w kolko, zaleznie od wyborow usera