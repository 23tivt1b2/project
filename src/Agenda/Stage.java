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
}
