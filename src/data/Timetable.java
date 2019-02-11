package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Timetable implements Serializable {

    private ArrayList<Stage> stages;

    public Timetable(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public void addStage(Stage stage) {
        this.stages.add(stage);
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
