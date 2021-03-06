package com.marekk.music;

import com.marekk.utilities.DataSource;
import com.marekk.utilities.Utilities;

import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Playlist playlist = new Playlist();

    private static boolean forbiddenAddingSongsRandomly = false;
    private static boolean forbiddenAddingSongsManually = false;
    private static boolean isSongsAddedRandomlyOnce = false;


    public static void main(String[] args) {
        programStart();
    }

    private static void programStart() {
        mainOptions();
    }

    public static void mainOptions() {
        Utilities.printWelcomeBanner();
        printMainOptions();
        boolean quitProgram = false;
        int numberOfSongsInPlaylist = playlist.getLibraryOfSongs().size();

        while (!quitProgram) {
            try {
                int userChoice = input.nextInt();
                input.nextLine();
                if (userChoice == 8) {
                    quitProgram = true;
                    DataSource.closeConnection();
                }

                switch (userChoice) {
                    case 1:
                        printMainOptions();
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
                        addSongToPlaylistManually(numberOfSongsInPlaylist);
                        Utilities.printBorderLines();
                        break;
                    case 5:
                        Utilities.printBorderLines();
                        addSongToPlaylistRandomly(numberOfSongsInPlaylist);
                        Utilities.printBorderLines();
                        break;
                    case 6:
                        // add own song to library, ask user whether he want to add it to playlist at once or not
                        // availability to add song to database
                    case 7:
                        Utilities.printBorderLines();
                        redirectToPlaylistController();
                        Utilities.printBorderLines();
                        break;
                    case 8:
                        input.close();
                        break;
                    default:
                        Utilities.printBorderLines();
                        System.out.println("Please type number in range 1-7.");
                        Utilities.printBorderLines();
                }
            } catch (InputMismatchException errorException) {
                System.out.println("ERROR! ONLY INTEGERS ALLOWED!!!");
                input.next();
            }
        }
    }

    private static void redirectToPlaylistController() {
        if (!playlist.getPlaylistOfSongs().isEmpty()) {
            playlistOptions();
        } else {
            System.out.println("Options not available if playlist is empty!");
        }
    }

    private static void addSongToPlaylistRandomly(int numberOfSongsInPlaylist) {
        if (forbiddenAddingSongsRandomly) {
            System.out.println("You cannot add randomly songs while You added at least one song manually!");
        } else {
            if (!isSongsAddedRandomlyOnce) {
                System.out.format("Type number of songs You want to add to playlist (within range 1-%d):\n", numberOfSongsInPlaylist);
                int userInputNumber = input.nextInt();
                while (userInputNumber < 1 || userInputNumber > numberOfSongsInPlaylist) {
                    System.out.format("Chosen amount of songs is out of library range! Please type number within range 1-%d\n", numberOfSongsInPlaylist);
                    userInputNumber = input.nextInt();
                }

                input.nextLine();
                generateRandomListOfSongs(userInputNumber);
                System.out.println("Songs added randomly to playlist.");
                isSongsAddedRandomlyOnce = true;
                forbiddenAddingSongsManually = true;

            } else {
                System.out.println("You can use this option only once!");
            }
        }
    }

    private static void addSongToPlaylistManually(int numberOfSongsInPlaylist) {
        if (forbiddenAddingSongsManually) {
            System.out.println("You cannot add manually songs while You added them randomly!");
        } else {
            System.out.format("Type below position of a song from library (1-%d):\n", numberOfSongsInPlaylist);
            int userSongNumber = (input.nextInt()) - 1;
            while (userSongNumber < 0 || userSongNumber > numberOfSongsInPlaylist) {
                System.out.format("Chosen song is out of library range! Please type number within range 1-%d\n", numberOfSongsInPlaylist);
                userSongNumber = (input.nextInt()) - 1;
            }
            String userSongTitle = playlist.getLibraryOfSongs().get(userSongNumber).getSongTitle();
            if (!playlist.isSongAlreadyInPlaylist(userSongTitle)) {
                if (playlist.addSongToPlaylist(userSongTitle)) {
                    System.out.println("Song '" + userSongTitle + "' successfully added to current playlist");
                    forbiddenAddingSongsRandomly = true;
                } else {
                    System.out.println("Song '" + userSongTitle + "' not found in library!" +
                            "\nRequest aborted.");
                }
            } else {
                System.out.println("Song '" + userSongTitle + "' is already on playlist!");
            }
        }
    }

    private static void playlistOptions() {
        printPlaylistOptions();
        ListIterator<Song> songListIterator = playlist.getPlaylistOfSongs().listIterator();
        boolean isTimeToBack = false;
        boolean isPlaylistStarted = false;
        boolean goingForward = true;

        while (!isTimeToBack) {
            try {
                int userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice) {
                    case 1:
                        Utilities.printBorderLines();
                        isPlaylistStarted = startPlaylist(songListIterator, isPlaylistStarted);
                        Utilities.printBorderLines();
                        break;
                    case 2:
                        Utilities.printBorderLines();
                        goingForward = nextSong(songListIterator, isPlaylistStarted, goingForward);
                        Utilities.printBorderLines();
                        break;
                    case 3:
                        Utilities.printBorderLines();

                        if (isPlaylistStarted) {
                            if (goingForward) {
                                if (songListIterator.hasPrevious()) {
                                    songListIterator.previous();
                                }
                                goingForward = false;
                            }
                            if (songListIterator.hasPrevious()) {
                                String previousSongTitle = songListIterator.previous().getSongTitle();
                                System.out.println("Skipped to previous song\n Now playing: '" + previousSongTitle + "'");
                            } else {
                                System.out.println("Beginning of playlist reached.");
                                goingForward = true;
                            }
                        } else {
                            System.out.println("Option not available now, please press '1' to start playlist.");
                        }

                        Utilities.printBorderLines();
                        break;
                    case 4:
                        Utilities.printBorderLines();

                        if (isPlaylistStarted) {
                            if (!goingForward) {
                                if (songListIterator.hasNext()) {
                                    songListIterator.next();
                                }
                                goingForward = true;
                            }
                            if (songListIterator.hasPrevious()) {
                                songListIterator.previous();
                                String toReplaySongTitle = songListIterator.next().getSongTitle();
                                System.out.println("Replaying song: '" + toReplaySongTitle + "'");
                            } else if (songListIterator.hasNext() && !playlist.getPlaylistOfSongs().isEmpty()) {
                                String toReplaySongTitle = songListIterator.next().getSongTitle();
                                System.out.println("Replaying song: '" + toReplaySongTitle + "'");
                            } else {
                                System.out.println("Replay not possible, playlist is empty.");
                            }
                        } else {
                            System.out.println("Option not available now, please press '1' to start playlist.");
                        }

                        Utilities.printBorderLines();
                        break;
                    case 5:
                        Utilities.printBorderLines();

                        if (!playlist.getPlaylistOfSongs().isEmpty()) {
                            if (isPlaylistStarted) {
                                songListIterator.remove();
                                if (songListIterator.hasNext()) {
                                    System.out.println("Current song was removed from playlist\n" +
                                            " Playing next song: '" + songListIterator.next().getSongTitle() + "'");
                                } else if (songListIterator.hasPrevious()) {
                                    System.out.println("Current song was removed from playlist\n" +
                                            "Removed song was on last position of playlist\n" +
                                            " Playing previous song: '" + songListIterator.previous().getSongTitle() + "'");
                                } else {
                                    System.out.println("All song was removed from playlist, now it's empty.");
                                    forbiddenAddingSongsManually = false;
                                    forbiddenAddingSongsRandomly = false;
                                }
                            } else {
                                System.out.println("Option not available now, please press '1' to start playlist.");
                            }
                        } else {
                            System.out.println("All song was removed from playlist, now it's empty.");
                            forbiddenAddingSongsManually = false;
                            forbiddenAddingSongsRandomly = false;
                        }

                        Utilities.printBorderLines();
                        break;
                    case 6:
                        Utilities.printBorderLines();

                        if (!playlist.getPlaylistOfSongs().isEmpty()) {
                            playlist.getPlaylistOfSongs().clear();
                            System.out.println("Playlist emptied!");
                            isSongsAddedRandomlyOnce = false;
                            forbiddenAddingSongsManually = false;
                            forbiddenAddingSongsRandomly = false;
                            isTimeToBack = true;
                        } else {
                            System.out.println("Playlist is already empty!");
                            printPlaylistOptions();
                        }

                        Utilities.printBorderLines();
                        printMainOptions();
                        break;
                    case 7:
                        Utilities.printBorderLines();
                        playlist.printSongsInPlaylist();
                        Utilities.printBorderLines();
                        break;
                    case 8:
                        printMainOptions();
                        isTimeToBack = true;
                        break;
                    default:
                        Utilities.printBorderLines();
                        System.out.println("Please type number within range 1-7.");
                        Utilities.printBorderLines();
                }
            } catch (InputMismatchException errorException) {
                System.out.println("ERROR! ONLY INTEGERS ALLOWED!!!");
                input.next();
            }
        }
    }

    private static boolean nextSong(ListIterator<Song> songListIterator, boolean isPlaylistStarted, boolean goingForward) {
        if (isPlaylistStarted) {
            if (!goingForward) {
                if (songListIterator.hasNext()) {
                    songListIterator.next();
                }
                goingForward = true;
            }
            if (songListIterator.hasNext()) {
                String nextSongTitle = songListIterator.next().getSongTitle();
                System.out.println("Skipped to next song\n Now playing: '" + nextSongTitle + "'");
            } else {
                System.out.println("End of playlist reached.");
                goingForward = false;
            }
        } else {
            System.out.println("Option not available now, please press '1' to start playlist.");
        }
        return goingForward;
    }

    private static boolean startPlaylist(ListIterator<Song> songListIterator, boolean isPlaylistStarted) {
        if (!isPlaylistStarted) {
            String songTitlePlayingNow = songListIterator.next().getSongTitle();
            System.out.println("Playlist started\n Now playing: '" + songTitlePlayingNow + "'");
            isPlaylistStarted = true;
        } else {
            System.out.println("Playlist already started!");
        }
        return isPlaylistStarted;
    }

    private static void printMainOptions() {
        Utilities.printBorderLines();
        System.out.println("> Main Options <" +
                "\n1 - > Show all options" +
                "\n2 - > Show all songs in current playlist" +
                "\n3 - > Show all songs in entire library" +
                "\n4 - > Add specific song to playlist" +
                "\n5 - > Add random songs to playlist" +
                "\n6 - > Add song to library" +
                "\n7 - >> Redirect to options of playlist controller" +
                "\n8 - >>> Quit Program");
        Utilities.printBorderLines();
    }

    private static void printPlaylistOptions() {
        Utilities.printBorderLines();
        System.out.println("> Playlist Options <" +
                "\n1 - > Start playlist" +
                "\n2 - > Play next song" +
                "\n3 - > Play previous song" +
                "\n4 - > Replay song" +
                "\n5 - > Remove current song from playlist" +
                "\n6 - > Remove all songs from playlist" +
                "\n7 - > Show playlist" +
                "\n8 - >> Back to main options");
        Utilities.printBorderLines();
    }

    private static void generateRandomListOfSongs(int numberOfSongs) {
        Random random = new Random();
        while (playlist.getPlaylistOfSongs().size() != numberOfSongs) {
            int currentRandomNumber = random.nextInt(playlist.getLibraryOfSongs().size());
            String randomSongTitle = playlist.getLibraryOfSongs().get(currentRandomNumber).getSongTitle();
            if (!playlist.isSongAlreadyInPlaylist(randomSongTitle)) {
                playlist.addSongToPlaylist(randomSongTitle);
            }
        }
    }

}

//TODO optimize generateRandomListOfSong method - too slow.