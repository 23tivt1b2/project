package Agenda;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Stage> stages;

    public Agenda(ArrayList<Stage> stages) {
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
