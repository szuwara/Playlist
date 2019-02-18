package music;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Playlist playlist = new Playlist();
    private static boolean isSongsAddedRandomlyOnce = false;

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
            boolean isTimeToBackToMainMenu = false;
            try {
                int primaryChoice = input.nextInt();
                input.nextLine();
                userPrimaryChoice(primaryChoice);

                if (primaryChoice == 6 && !playlist.getPlaylistOfSongs().isEmpty()) {
                    while (!isTimeToBackToMainMenu) {
                        int secondaryChoice = input.nextInt();
                        input.nextLine();
                        userSecondaryChoice(secondaryChoice);
                        if (secondaryChoice == 6) {
                            isTimeToBackToMainMenu = true;
                        }
                    }
                } else if (primaryChoice == 7) {
                    quitProgram = true;
                }
            } catch (InputMismatchException errorException) {
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
                Utilities.printBorderLines();
                playlist.printSongsInPlaylist();
                Utilities.printBorderLines();
                break;
            case 3:
                Utilities.printBorderLines();
                playlist.printAllSongsInLibrary();
                Utilities.printBorderLines();
                break;
            case 4:
                Utilities.printBorderLines();
                System.out.println("Type below position of a song from library:");
                int userSongNumber = (input.nextInt()) - 1;
                String userSongTitle = playlist.getLibraryOfSongs().get(userSongNumber).getTitle();
                if (!playlist.isSongAlreadyInPlaylist(userSongTitle)) {
                    if (playlist.addSongToPlaylist(userSongTitle)) {
                        System.out.println("Song '" + userSongTitle + "' successfully added to current playlist!");
                    } else {
                        System.out.println("Song '" + userSongTitle + "' not found in library!" +
                                "\nRequest aborted");
                    }
                } else {
                    System.out.println("Song '" + userSongTitle + "' is already on playlist!");
                }

                Utilities.printBorderLines();
                break;
            case 5:
                Utilities.printBorderLines();
                if (!isSongsAddedRandomlyOnce) {
                    System.out.println("Type number of songs You want to add to playlist (within range 1-" + playlist.getLibraryOfSongs().size() + "):");
                    int userInputNumber = input.nextInt();
                    generateRandomListOfSongs(userInputNumber);
                    isSongsAddedRandomlyOnce = true;
                    System.out.println("Songs added randomly to playlist!");
                } else {
                    System.out.println("You can use this option only once!");
                }
                Utilities.printBorderLines();
                break;
            case 6:
                Utilities.printBorderLines();
                if (!playlist.getPlaylistOfSongs().isEmpty()) {
                    printSecondaryOptions();
                } else {
                    System.out.println("Options not available if playlist is empty!");
                }
                Utilities.printBorderLines();
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
                System.out.println("Now playing: " + playlist.getPlaylistOfSongs().getFirst().getTitle());
                break;
            case 2: //skip next
                System.out.println("Next");
                break;
            case 3: //previous
                System.out.println("Previous");
                break;
            case 4: //remove from playlist
                System.out.println("Removing");
                break;
            case 5: //replay
                System.out.println("Replay");
                break;
            case 6:
                printPrimaryOptions();
                break;
            default:
                Utilities.printBorderLines();
                System.out.println("Please type number within range 1-6");
                Utilities.printBorderLines();
        }
    }

    private static void printPrimaryOptions() {
        System.out.println(">> PLAYLIST APPLICATION CONTROLLER <<" +
                "\nOptions:" +
                "\n1 - >Show all options" +
                "\n2 - >Show all songs in current playlist" +
                "\n3 - >Show all songs in entire library" +
                "\n4 - >Add specific song to playlist" +
                "\n5 - >Add random songs to playlist" + //create feature
                "\n6 - >>Redirect to options of playlist controller" +
                "\n7 - >>>Quit Program");
    }

    private static void printSecondaryOptions() {
        System.out.println("> CONTROLS <" +
                "\n1 - Play" +
                "\n2 - Skip to next song" +
                "\n3 - Skip to previous song" +
                "\n4 - Remove song from playlist" +
                "\n5 - Replay song" +
                "\n6 - Back to main options");
    }

    private static void generateRandomListOfSongs(int numberOfSongs) {
        Random random = new Random();
        while (playlist.getPlaylistOfSongs().size() != numberOfSongs) {
            int currentNum = random.nextInt(playlist.getLibraryOfSongs().size());
            String randomSongTitle = playlist.getLibraryOfSongs().get(currentNum).getTitle();
            if (!playlist.isSongAlreadyInPlaylist(randomSongTitle)) {
                playlist.addSongToPlaylist(randomSongTitle);
            }
        }
    }
}