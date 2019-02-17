package gui.agenda1;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TimeBox {

    private HBox timeBox;

    private TextField topLeftIndicator;

    public void createTimeBox(BorderPane secondaryBorderPane) {
        this.topLeftIndicator = new TextField("stage / time");
        this.topLeftIndicator.setDisable(true);
        this.topLeftIndicator.setMinSize(90, 30);

        this.timeBox = new HBox();
        this.timeBox.setMinSize(60, 30);
        this.timeBox.setMaxSize(60, 30);

        this.timeBox.getChildren().add(this.topLeftIndicator);

        for(int k = 1; k < 25; k++) {
            TextField time = new TextField(k + ":00");
            time.setDisable(true);
            time.setOpacity(1);
            time.setMinSize(60, 30);
            this.timeBox.getChildren().add(time);
        }

        secondaryBorderPane.setTop(this.timeBox);
    }

    public void update(data.Timetable timetable) {

    }
}
