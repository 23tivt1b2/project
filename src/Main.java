import data.Artist;
import data.Performance;
import data.Stage;
import data.Timetable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String [ ] args) {
//        Artist neffex = new Artist("Neffex", 32);
//        Artist lilwayne = new Artist("Lil Wayne", 52);
//        LocalTime time = LocalTime.of(12,0);
//        Performance performance = new Performance(neffex, time, time );
//        Stage stage = new Stage(performance, 200);
//        Timetable timetable = new Timetable(stage);
//        System.out.println(timetable.getStages().getPerformances().getArtists().getName());

        ArrayList<Stage> stages = new ArrayList<Stage>();
        ArrayList<Performance> performances = new ArrayList<>();
        ArrayList<Artist> artists = new ArrayList<>();

        artists.add(new Artist("Paul Lindelauf", 1));
        LocalTime start = LocalTime.of(11, 0, 0, 0);
        LocalTime end = LocalTime.of(13, 0, 0);
        performances.add(new Performance(artists, start, end));
        stages.add(new Stage(performances, 200));
        Timetable timeTable = new Timetable(stages);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data/person.bin"));
            objectOutputStream.writeObject(timeTable);
            objectOutputStream.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/person.bin"));
            Timetable readTimeTable = (Timetable) objectInputStream.readObject();
            System.out.println(readTimeTable);
            objectInputStream.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
