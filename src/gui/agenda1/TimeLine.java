package gui.agenda1;

import java.awt.*;
import javafx.scene.Node.*;

import com.sun.prism.paint.Color;
import data.Performance;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint.*;

import java.util.ArrayList;

public class TimeLine {

    private HBox timeLine;

    private ArrayList<Integer> performaces;

    private GridPane timeLines;


    TimeLine(BorderPane borderPane, data.Timetable timetable) {
        this.timeLines = new GridPane();
        borderPane.setCenter(this.timeLines);

        update(timetable);
    }

    public void update(data.Timetable timetable) {
        int counter = 1;
        Scrollbar scrollbar = new Scrollbar();
        this.timeLines.getChildren().clear();
        for (data.Stage stage : timetable.getStages()) {
            HBox temporary = new HBox();
            for (int i = 1; i < 25; i++) {
                Label beauty = new Label();
                beauty.setStyle("-fx-background-color: #D7D4E5; "
                                + "-fx-border-color: white");
                beauty.setOpacity(1);
                beauty.setMinSize(60, 30);
                beauty.setMaxSize(60, 30);
                for(Performance performance : stage.getPerformances()) {
                    if (performance.getBeginTime().getHour() <= i && performance.getEndTime().getHour() > i) {
                        beauty.setStyle("-fx-background-color: #475069; "
                        + "-fx-text-fill: white");
                    }
                    if (performance.getBeginTime().getHour() == i) {
                        beauty.setText(performance.getArtist().getName());
                    }
                }
                temporary.getChildren().add(beauty);

            }
            this.timeLines.add(temporary, 0, counter);
            counter++;
        }
    }

}
