package gui.pages.timetable;

import data.Performance;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Timeline {

    private Color LIGHT_FILL_1 = Color.rgb(148,1,135);

    private VBox timeline;

    public Timeline(BorderPane borderPane, data.Timetable timetable) {
        this.timeline = new VBox();
        borderPane.setCenter(this.timeline);
        update(timetable);
    }
    public void update(data.Timetable timetable) {
        this.timeline.getChildren().clear();
        for (data.Stage stage : timetable.getStages()) {
            HBox temporary = new HBox();
            for (int i = 1; i < 25; i++) {
                Button temporary2 = new Button();
                temporary2.setDisable(true);
                temporary2.setOpacity(0.5);
                temporary2.setMinSize(60, 30);
                temporary2.setMaxSize(60, 30);
                for(Performance performance : stage.getPerformances()) {
                    if (performance.getBeginTime().getHour() <= i && performance.getEndTime().getHour() > i) {
                        temporary2.setText(performance.toString());
                        temporary2.setOpacity(1);
                    }
                }
                temporary.getChildren().add(temporary2);
            }
            this.timeline.getChildren().add(temporary);
        }
    }
}
