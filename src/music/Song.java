package music;


class Song {

    private String title;
    private int duration;

    Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    String getTitle() {
        return title;
    }

    int getDuration() {
        return duration;
    }
}
