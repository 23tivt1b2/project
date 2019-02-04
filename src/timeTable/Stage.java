package timeTable;

import java.util.ArrayList;

public class Stage {
    private int maxBezoekers;
    private ArrayList<Performance> performances;

    Stage(ArrayList<Performance> performances, int maxBezoekers) {
        this.performances = performances;
        this.maxBezoekers = maxBezoekers;
    }

    public void addPerfomance(Performance performance) {
        this.performances.add(performance);
    }

    public void setMaxBezoekers(int maxBezoekers) {
        this.maxBezoekers = maxBezoekers;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public int getMaxBezoekers() {
        return maxBezoekers;
    }

    public ArrayList <Performance> getPerformances() {
        return performances;
    }
}
