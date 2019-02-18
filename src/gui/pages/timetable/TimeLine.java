package gui.pages.timetable;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TimeLine {

    private HBox timeLine;

    TimeLine(data.Stage stage, VBox vBox) {
        this.timeLine = new HBox();
        for(int i = 1; i < 25; i++) {
            Button temporary = new Button();
            temporary.setDisable(true);
            temporary.setOpacity(0.5);
            temporary.setMinSize(60, 30);
            this.timeLine.getChildren().add(temporary);
        }
        vBox.getChildren().add(this.timeLine);
    }

    public void update() {

    }
}
