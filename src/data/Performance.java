package data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Performance implements Serializable {

    private Artist artist;
    private LocalTime beginTime;
    private LocalTime endTime;

    public Performance(Artist artist,LocalTime beginTime, LocalTime endTime) {
        this.artist = artist;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public void addArtist(Artist name) {
//       artist.add(name);
    }

//    public void setArtists(ArrayList<Artist> artists) {
//        this.artist = artists;
//    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

//    public ArrayList<Artist> getArtists() {
//        return artists;
//    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
