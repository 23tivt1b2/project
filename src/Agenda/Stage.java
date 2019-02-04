package Agenda;

import java.time.LocalTime;

public class Stage {
    private int maxVisitors;

    Stage(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public void addPerfomance(LocalTime beginTime, LocalTime endTime) {
        Performance performance = new Performance(beginTime, endTime);
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
