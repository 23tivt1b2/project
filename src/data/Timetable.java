package data;


import gui.pages.timetable.StageBox;

import java.io.Serializable;
import java.util.ArrayList;

public class Timetable implements Serializable {

    private ArrayList<Stage> stages;
    private ArrayList<Artist> artists;

    public Timetable() {
        this.stages = new ArrayList<>();
        this.artists = new ArrayList<>();
    }

    public void addStage(Stage stage, StageBox stageBox) {
        this.stages.add(stage);
        stageBox.update(this);
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public ArrayList<Stage> getStages() {
        return this.stages;
    }

    public Stage getIndex(int i) {
        return this.stages.get(i);
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public ArrayList<Artist> getArtists() {
        return this.artists;
    }

    public int getSize() {
        return this.stages.size();
    }

    public void updateTimeTableInterface(StageBox stageBox) {
        stageBox.update(this);
    }
}
