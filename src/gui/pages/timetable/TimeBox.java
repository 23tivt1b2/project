package gui.pages.timetable;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TimeBox {

    private HBox timeBox;

    private TextField topLeftIndicator;

    public void createTimeBox(BorderPane secondaryBorderPane) {
        this.topLeftIndicator = new TextField("stage / time");
        this.topLeftIndicator.setMinSize(90, 30);

        this.timeBox = new HBox();
        this.timeBox.setMinSize(30, 30);
        this.timeBox.setMaxSize(30, 30);
        this.timeBox.setPadding(new Insets(0,0,0,0));

        this.timeBox.getChildren().add(this.topLeftIndicator);

        for(int k = 13; k < 25; k++) {
            TextField time = new TextField(k + ":00");
            time.getStyleClass().add("time");
            time.setDisable(true);
            time.setOpacity(1);
            time.setMinSize(60, 30);
            time.setPadding(new Insets(0,0,0,0));
            this.timeBox.getChildren().add(time);
        }
        secondaryBorderPane.setTop(this.timeBox);
    }

    public void update(data.Timetable timetable) {

    }
}
