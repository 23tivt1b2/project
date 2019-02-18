package gui.agenda1;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TimeBox {

    private HBox timeBox;

    private Label topLeftIndicator;

    public void createTimeBox(BorderPane secondaryBorderPane) {
        this.topLeftIndicator = new Label("  Stage/time");
        this.topLeftIndicator.setStyle("-fx-border-color: black");
        this.topLeftIndicator.setMinSize(90, 30);

        this.timeBox = new HBox();
        this.timeBox.setMinSize(60, 30);
        this.timeBox.setMaxSize(60, 30);

        this.timeBox.getChildren().add(this.topLeftIndicator);

        for(int k = 1; k < 25; k++) {
            Label time = new Label();
            if(k == 24) {
                time.setText("00" + ":00");
            } else {
                time.setText(k + ":00");
            }
            time.setStyle("-fx-right-border-color: black");
            time.setOpacity(1);
            time.setMinSize(60, 30);
            this.timeBox.getChildren().addAll(time);
        }

        secondaryBorderPane.setTop(this.timeBox);

    }

    public void update(data.Timetable timetable) {

    }
}
