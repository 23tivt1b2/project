import data.Artist;
import data.Performance;
import data.Stage;
import timeTable.Timetable;

import java.time.LocalTime;

public class Main {

    public static void main(String [ ] args) {
        Artist neffex = new Artist("Neffex", 32);
        Artist lilwayne = new Artist("Lil Wayne", 52);
        LocalTime time = LocalTime.of(12,0);
        Performance performance = new Performance(neffex, time, time );
        Stage stage = new Stage(performance, 200);
        Timetable timetable = new Timetable(stage);
    }

}
