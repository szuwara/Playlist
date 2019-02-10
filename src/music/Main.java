package music;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Playlist playlist = new Playlist();

    public static void main(String[] args) {
        Song yellow = new Song("Yellow", 180);
        Song parachutes = new Song("Parachutes", 120);
        Song shiver = new Song("Shiver", 160);
        Song enl = new Song("Everything's Not Lost", 200);
        Album parachutesAlbum = new Album("Parachutes", "Coldplay");
        Collections.addAll(parachutesAlbum.getAlbumSongs(), yellow, parachutes, shiver, enl);

        Song politik = new Song("Politik", 190);
        Song inMyPlace = new Song("In My Place", 210);
        Song theScientist = new Song("The Scientist", 240);
        Song clocks = new Song("Clocks", 150);
        Album aRushOfBloodToTheHeadAlbum = new Album("A Rush of Blood To The Head", "Coldplay");
        Collections.addAll(aRushOfBloodToTheHeadAlbum.getAlbumSongs(), politik, inMyPlace, theScientist, clocks);

        Collections.addAll(playlist.getLibraryOfAlbums(), parachutesAlbum, aRushOfBloodToTheHeadAlbum);

        initializeLibrary();

        playlist.addSongToPlaylist(yellow, parachutesAlbum);
        playlist.printSongsInPlaylist();
        playlist.printAllSongsInLibrary();


    }

    static void initializeLibrary() {
        playlist.setLibraryOfSongs();
    }

}

//TODO a gdyby tak dodać pole artist do klasy Song?? ze jak zainicjalizujemy album i artyste, dodamy piosenki to piosenki "pochlona" nazwe artysty od razu CO TY NATO ???
//TODO jeszcze metoda playlist.printAllSongsInLibrary robi to samo praktycznie co playlist.setLibraryOfSongs, po co dwie implementacje tego samego (zmienic ale i zrobic tak zeby bylo widac "by artist")
