package music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Playlist playlist = new Playlist();

    private static boolean forbiddenAddingSongsRandomly = false;
    private static boolean forbiddenAddingSongsManually = false;

    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kozlo\\JAVA Projects\\Playlist\\resources\\music.db");
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS test (_id INTEGER PRIMARY KEY, name TEXT)");

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
        {
            /*Song yellow = new Song("Yellow", 180);
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
            initializeLibrary(parachutesAlbum, onlyByTheNightAlbum, comeAroundSundownAlbum);*/
        }

        mainOptions();
    }

    private static void initializeLibrary(Album album1, Album album2, Album album3) {
        Collections.addAll(playlist.getLibraryOfAlbums(), album1, album2, album3);
        playlist.setLibraryOfSongs();
    }

    private static void mainOptions() {
        Utilities.printWelcomeBanner();
        printMainOptions();
        boolean isSongsAddedRandomlyOnce = false;
        boolean quitProgram = false;

        while (!quitProgram) {
            try {
                int userChoice = input.nextInt();
                input.nextLine();
                if (userChoice == 7) {
                    quitProgram = true;
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

                        if (forbiddenAddingSongsManually) {
                            System.out.println("You cannot add manually songs while You added them randomly!");
                        } else {
                            System.out.println("Type below position of a song from library:");
                            int userSongNumber = (input.nextInt()) - 1;
                            String userSongTitle = playlist.getLibraryOfSongs().get(userSongNumber).getTitle();
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

                        Utilities.printBorderLines();
                        break;
                    case 5:
                        Utilities.printBorderLines();

                        if (forbiddenAddingSongsRandomly) {
                            System.out.println("You cannot add randomly songs while You added at least one song manually!");
                        } else {
                            if (!isSongsAddedRandomlyOnce) {
                                System.out.println("Type number of songs You want to add to playlist (within range 1-" + playlist.getLibraryOfSongs().size() + "):");
                                int userInputNumber = input.nextInt();
                                generateRandomListOfSongs(userInputNumber);
                                System.out.println("Songs added randomly to playlist.");
                                isSongsAddedRandomlyOnce = true;
                                forbiddenAddingSongsManually = true;
                            } else {
                                System.out.println("You can use this option only once!");
                            }
                        }

                        Utilities.printBorderLines();
                        break;
                    case 6:
                        Utilities.printBorderLines();

                        if (!playlist.getPlaylistOfSongs().isEmpty()) {
                            playlistOptions();
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
                        System.out.println("Please type number in range 1-7.");
                        Utilities.printBorderLines();
                }
            } catch (InputMismatchException errorException) {
                System.out.println("ERROR! ONLY INTEGERS ALLOWED!!!");
                input.next();
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

                        if (!isPlaylistStarted) {
                            String songTitlePlayingNow = songListIterator.next().getTitle();
                            System.out.println("Playlist started\n Now playing: '" + songTitlePlayingNow + "'");
                            isPlaylistStarted = true;
                        } else {
                            System.out.println("Playlist already started!");
                        }

                        Utilities.printBorderLines();
                        break;
                    case 2:
                        Utilities.printBorderLines();

                        if (isPlaylistStarted) {
                            if (!goingForward) {
                                if (songListIterator.hasNext()) {
                                    songListIterator.next();
                                }
                                goingForward = true;
                            }
                            if (songListIterator.hasNext()) {
                                String nextSongTitle = songListIterator.next().getTitle();
                                System.out.println("Skipped to next song\n Now playing: '" + nextSongTitle + "'");
                            } else {
                                System.out.println("End of playlist reached.");
                                goingForward = false;
                            }
                        } else {
                            System.out.println("Option not available now, please press '1' to start playlist.");
                        }

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
                                String previousSongTitle = songListIterator.previous().getTitle();
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
                                String toReplaySongTitle = songListIterator.next().getTitle();
                                System.out.println("Replaying song: '" + toReplaySongTitle + "'");
                            } else if (songListIterator.hasNext() && !playlist.getPlaylistOfSongs().isEmpty()) {
                                String toReplaySongTitle = songListIterator.next().getTitle();
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
                                            " Playing next song: '" + songListIterator.next().getTitle() + "'");
                                } else if (songListIterator.hasPrevious()) {
                                    System.out.println("Current song was removed from playlist\n" +
                                            "Removed song was on last position of playlist\n" +
                                            " Playing previous song: '" + songListIterator.previous().getTitle() + "'");
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
                        playlist.printSongsInPlaylist();
                        Utilities.printBorderLines();
                        break;
                    case 7:
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

    private static void printMainOptions() {
        Utilities.printBorderLines();
        System.out.println("> Main Options <" +
                "\n1 - > Show all options" +
                "\n2 - > Show all songs in current playlist" +
                "\n3 - > Show all songs in entire library" +
                "\n4 - > Add specific song to playlist" +
                "\n5 - > Add random songs to playlist" + //create feature
                "\n6 - >> Redirect to options of playlist controller" +
                "\n7 - >>> Quit Program");
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
                "\n6 - > Show playlist" +
                "\n7 - >> Back to main options");
        Utilities.printBorderLines();
    }

    private static void generateRandomListOfSongs(int numberOfSongs) {
        Random random = new Random();
        while (playlist.getPlaylistOfSongs().size() != numberOfSongs) {
            int currentRandomNumber = random.nextInt(playlist.getLibraryOfSongs().size());
            String randomSongTitle = playlist.getLibraryOfSongs().get(currentRandomNumber).getTitle();
            if (!playlist.isSongAlreadyInPlaylist(randomSongTitle)) {
                playlist.addSongToPlaylist(randomSongTitle);
            }
        }
    }
}