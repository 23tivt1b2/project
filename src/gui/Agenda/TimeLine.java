package gui.Agenda;

import data.Performance;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class Agenda {

    private GridPane agenda;

    public void createAgenda(BorderPane secondaryBorderPane, data.Timetable timetable) {
        this.agenda = new GridPane();

        secondaryBorderPane.setCenter(this.agenda);

        int j = 1;
        for (int i = 0; i < 24; i++) {
            Button temporary = new Button();
            temporary.setMinSize(60, 30);
            temporary.setMaxSize(60, 30);
            temporary.setDisable(true);
            temporary.setOpacity(0.2);
            this.agenda.add(temporary, i, 0);
        }
        for (data.Stage stage : timetable.getStages()) {
            Button temporary = new Button();
            for (int i = 0; i < 25; i++) {
                this.agenda.add(temporary, i, j);
            }
            j++;
        }
    }

    public void addStage(data.Timetable timetable) {
        int j = timetable.getSize();
        for (int i = 0; i < 24; i++) {
            Button temporary = new Button();

            temporary.setMinSize(60, 30);
            temporary.setMaxSize(60, 30);
            temporary.setOpacity(0.5);
            this.agenda.add(temporary, i, j);
        }
    }
}
