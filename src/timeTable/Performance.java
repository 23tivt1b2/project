package timeTable;

import java.time.LocalTime;
import java.util.ArrayList;

public class Performance {

    private ArrayList<Artist> artists;
    private LocalTime beginTime;
    private LocalTime endTime;

    public Performance(ArrayList<Artist> artists, LocalTime beginTime, LocalTime endTime){
        this.artists = artists;
        this.beginTime = beginTime;
        this.endTime = endTime;

    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
