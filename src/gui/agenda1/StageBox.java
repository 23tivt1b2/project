package gui.agenda1;

import data.Artist;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        VBox stageOptionMenu = new VBox();
        Scene stageOptionMenuScene = new Scene(stageOptionMenu);
        Stage stageOptionMenuStage = new Stage();

        Button delete = new Button("delete");
        delete.setMinSize(120, 30);
        delete.setMaxSize(120, 30);
        delete.setOnMouseClicked(event -> {
            deleteStage(stage, timetable);
            this.timeLine.update(timetable);
            stageOptionMenuStage.close();
        });
        Button addPerformance = new Button("add performance");
        addPerformance.setMinSize(120, 30);
        addPerformance.setMaxSize(120, 30);
        addPerformance.setOnMouseClicked(event -> {
            addPerformance(stage, event.getX(), event.getY(), timetable);
        });
        Button setMaxVisitors = new Button("set max visitor's");
        setMaxVisitors.setMinSize(120, 30);
        setMaxVisitors.setMaxSize(120, 30);
        setMaxVisitors.setOnMouseClicked(event -> {
            setMaxVisitors(stage, event.getX(), event.getY(), timetable);
            stageOptionMenuStage.close();
        });

        stageOptionMenu.getChildren().addAll(delete, addPerformance, setMaxVisitors);

        stageOptionMenuStage.setX(x);
        stageOptionMenuStage.setY(y);
        stageOptionMenuStage.setScene(stageOptionMenuScene);
        stageOptionMenuStage.show();
    }

    public void deleteStage(data.Stage stage, data.Timetable timetable) {
                timetable.getStages().remove(stage);
                timetable.updateTimeTableInterface(this);
    }

    public void setMaxVisitors(data.Stage stage, double x, double y, data.Timetable timetable) {
        VBox setMaxVisitorsMenu = new VBox();
        Scene stageOptionMenuScene = new Scene(setMaxVisitorsMenu);
        Stage stageOptionMenuStage = new Stage();
        TextField setMaxVisitors = new TextField("max visitor's");
        setMaxVisitors.setMinSize(120, 30);
        setMaxVisitors.setMaxSize(120, 30);
        setMaxVisitors.setOnKeyTyped(event -> {
            setMaxVisitors.setText("");
        });
        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            stage.setMaxVisitors(Integer.valueOf(setMaxVisitors.getText()));
            stageOptionMenuStage.close();
        });

        setMaxVisitorsMenu.getChildren().addAll(setMaxVisitors, confirm);

        stageOptionMenuStage.setX(x);
        stageOptionMenuStage.setY(y);
        stageOptionMenuStage.setScene(stageOptionMenuScene);
        stageOptionMenuStage.show();
    }

    public void addPerformance(data.Stage stage, double x, double y, data.Timetable timetable) {
        VBox stageOptionMenu = new VBox();
        Scene stageOptionMenuScene = new Scene(stageOptionMenu);
        Stage stageOptionMenuStage = new Stage();

        ComboBox<Artist> artistSelector = new ComboBox<>();
        artistSelector.setMinSize(120, 30);
        artistSelector.setMaxSize(120, 30);
        for (Artist artist : timetable.getArtists()) {
            artistSelector.getItems().add(artist);
        }

        TextField performanceBeginTime = new TextField("begin time");
        performanceBeginTime.setMinSize(120, 30);
        performanceBeginTime.setMaxSize(120, 30);

        TextField performanceEndTime = new TextField("end time");
        performanceEndTime.setMinSize(120, 30);
        performanceEndTime.setMaxSize(120, 30);

        Button confirm = new Button("confirm");
        confirm.setOnMouseClicked(event -> {
            stage.addPerfomance(new Performance(artistSelector.getValue(), LocalTime.parse(performanceBeginTime.getText()), LocalTime.parse(performanceEndTime.getText())), this.timeLine);
            stageOptionMenuStage.close();
            this.timeLine.update(timetable);
        });

        stageOptionMenu.getChildren().addAll(artistSelector, performanceBeginTime, performanceEndTime, confirm);

        stageOptionMenuStage.setX(x);
        stageOptionMenuStage.setY(y);
        stageOptionMenuStage.setScene(stageOptionMenuScene);
        stageOptionMenuStage.show();
    }
}
