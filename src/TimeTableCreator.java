import data.Artist;
import data.Performance;
import data.Stage;
import data.Timetable;

import java.util.ArrayList;

public class TimeTableCreator {
    private Timetable timeTable;

    private ArrayList<Performance> performances;
    private ArrayList<Stage> stages;
    private ArrayList<Artist> artists;

    public TimeTableCreator() {

    }

    public void updateTimeTable() {

    }

    public void addStage(ArrayList<Performance> performances, int maxVisitors) {
        this.timeTable.addStage(new Stage(performances, maxVisitors));
    }

    public void addArtist(String artistName, int populartiy) {
        this.artists.add(new Artist(artistName,populartiy));

    }


}
