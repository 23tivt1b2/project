package Agenda;

import java.time.LocalTime;
import java.util.ArrayList;

public class Stage {
    private int maxVisitors;

    Stage(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public void addPerfomance(LocalTime beginTime, LocalTime endTime) {
        Performance performance = new Performance(beginTime, endTime);
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }

    public void setPerformances(ArrayList<Performance> performances) {
    //    this.performances = performances;
    }

    public int getMaxBezoekers() {
        return maxVisitors;
    }

    //public ArrayList <Performance> getPerformances() {
//        return performances;
//    }
}
