package gui.agenda1;

import data.Artist;
import data.Performance;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalTime;

public class StageBox {

    private VBox stageBox;
    private TimeLine timeLine;

    public void createStageBox(BorderPane borderPane, data.Timetable timetable) {
        this.stageBox = new VBox();
        this.timeLine = new TimeLine(borderPane, timetable);
        this.stageBox.setMinWidth(60);
        this.stageBox.setMaxWidth(90);
        borderPane.setLeft(this.stageBox);
    }

    public void update(data.Timetable timetable) {
        this.stageBox.getChildren().clear();
        for (data.Stage stage : timetable.getStages()) {
            Button temporary = new Button(stage.toString());
            temporary.setMinSize(90, 30);
            temporary.setMaxSize(90, 30);
            temporary.setOnMouseClicked(event -> {
                stageOptionMenu(stage, timetable, event.getX(), event.getY());
            });
            this.stageBox.getChildren().add(temporary);
        }

        this.timeLine.update(timetable);
    }

    public void stageOptionMenu(data.Stage stage, data.Timetable timetable, double x, double y) {
        GridPane grid = new GridPane();
        Scene sceneOptionMenuScene = new Scene(grid, 330, 200);
        Stage stageOptionMenuStage = new Stage();

        Label maxVisitors = new Label("Set max visitors: ");
        maxVisitors.setTextFill(Color.BLACK);
        TextField setmaxVisitors = new TextField();
        Button accept = new Button("save");
        accept.setOnAction(event -> {
            stage.setMaxVisitors(Integer.valueOf(setmaxVisitors.getText()));
            setmaxVisitors.clear();
        });
        Label addPerformance = new Label("Add performance: ");
        ComboBox<Artist> artistComboBox = new ComboBox<>();
        for (Artist artist : timetable.getArtists()) {
            artistComboBox.getItems().add(artist);
        }

        Label setTime = new Label("Select time");
        ComboBox<LocalTime> beginTime = new ComboBox<>();
        ComboBox<LocalTime> endTime = new ComboBox<>();
        HBox comboBox = new HBox();
        comboBox.setSpacing(10);
        comboBox.getChildren().addAll(beginTime, endTime);

        Button acceptPerformance = new Button();
        acceptPerformance.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: 15px; " +
                        "-fx-min-height: 15px; " +
                        "-fx-max-width: 15px; " +
                        "-fx-max-height: 15px;"
        );

        Button delete = new Button("Delete");
        delete.setOnAction(event ->  {
            timetable.getStages().remove(stage);
            timetable.updateTimeTableInterface(this);
            stageOptionMenuStage.close();
        });
        Button save = new Button("Save");
        Button clear = new Button("Clear");
        Button exit = new Button("Exit");
        exit.setOnAction(event -> {
            stageOptionMenuStage.close();
        });
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(save, clear, delete, exit);

        grid.setHgap(10);
        grid.setVgap(12);
        grid.add(maxVisitors, 0, 1);
        grid.add(setmaxVisitors, 1, 1);
        grid.add(accept, 2, 1);
        grid.add(addPerformance, 0, 2);
        grid.add(artistComboBox, 1, 2);
        grid.add(setTime, 0, 3);
        grid.add(comboBox, 1, 3);
        grid.add(buttons, 0, 4, 4, 1);


        stageOptionMenuStage.setFullScreen(false);
        stageOptionMenuStage.setResizable(false);
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setStyle(
                "-fx-background-color: violet;"
                        + "-fx-background-radius: 8, 4;"
                        + "-fx-background-insets: 0;");


        stageOptionMenuStage.setTitle("Add artist");
        stageOptionMenuStage.setOpacity(0.9);

        sceneOptionMenuScene.setFill(Color.TRANSPARENT);
        stageOptionMenuStage.initStyle(StageStyle.TRANSPARENT);
        stageOptionMenuStage.setScene(sceneOptionMenuScene);
        stageOptionMenuStage.show();
    }
}
