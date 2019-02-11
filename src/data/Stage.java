package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Stage implements Serializable {
    private ArrayList<Performance> performances;
    private int maxVisitors;

   public Stage(ArrayList<Performance> performances,int maxVisitors) {
        this.performances = performances;
        this.maxVisitors = maxVisitors;
    }

    public void addPerfomance(Performance performance) {
        performances.add(performance);
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public int getMaxBezoekers() {
        return maxVisitors;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }
}
