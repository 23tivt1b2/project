package gui.agenda1;

import data.Performance;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TimeLine {

    private HBox timeLine;

    private ArrayList<Integer> performaces;

    private VBox timeLines;

    TimeLine(BorderPane borderPane, data.Timetable timetable) {
        this.timeLines = new VBox();
        borderPane.setCenter(this.timeLines);

        update(timetable);
    }

    public void update(data.Timetable timetable) {
        this.timeLines.getChildren().clear();
        for (data.Stage stage : timetable.getStages()) {
            HBox temporary = new HBox();
            for (int i = 1; i < 25; i++) {
                Button temporary2 = new Button();
                temporary2.setDisable(true);
                temporary2.setOpacity(0.5);
                temporary2.setMinSize(60, 30);
                temporary2.setMaxSize(60, 30);
                for(Performance performance : stage.getPerformances()) {
                    if (performance.getBeginTime().getHour() <= i && performance.getEndTime().getHour() >= i) {
                        temporary2.setText(performance.toString());
                        temporary2.setOpacity(1);
                    }
                }
                temporary.getChildren().add(temporary2);
            }
            this.timeLines.getChildren().add(temporary);
        }
    }

}
