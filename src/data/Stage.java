package data;

import gui.agenda1.TimeLine;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.ArrayList;

public class Stage implements Serializable {
    private ArrayList<Performance> performances;

    private int maxVisitors;
    private String stageName;

   public Stage(int maxVisitors, String name) {
        this.maxVisitors = maxVisitors;
        this.stageName = name;
        this.performances = new ArrayList<>();
    }

    public Stage() {
       this.maxVisitors = 0;
       this.stageName = "";
       this.performances = new ArrayList<>();
    }

    public void addPerfomance(Performance performance, TimeLine timeLine) {
        this.performances.add(performance);
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public int getMaxBezoekers() {
        return this.maxVisitors;
    }

    public String getStageName() {
       return this.stageName;
    }

    public void setStageName(String stageName) {
       this.stageName = stageName;
    }

    public ArrayList<Performance> getPerformances() {
        return this.performances;
    }

    @Override
    public String toString() {
       return this.stageName;
    }
}
