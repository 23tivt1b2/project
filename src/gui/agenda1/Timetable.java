package gui.agenda1;

import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Timetable {

    private data.Timetable timetable;

    private BorderPane primaryBorderPane;
    private BorderPane secondaryBorderPane;
    private BorderPane thirdBorderPane;

    private Stage primaryStage;
    private Stage secondaryStage;

    private Scene primaryScene;
    private Scene secondaryScene;

    private Button addArtist;

    private gui.agenda1.StageBox stageBox;
    private gui.agenda1.TimeBox timeBox;

    private HBox top;


    public void start() throws Exception {

        this.stageBox = new gui.agenda1.StageBox();
        this.timeBox = new gui.agenda1.TimeBox();

        this.timetable = new data.Timetable();

        createPrimaryBorderPane();

        this.primaryStage = new Stage();
        this.secondaryStage = new Stage();

        this.primaryScene = new Scene(this.primaryBorderPane);
        this.primaryStage.setScene(this.primaryScene);

        this.primaryStage.show();
    }

    public void createPrimaryBorderPane() {
        this.primaryBorderPane = new BorderPane();
        this.primaryBorderPane.autosize();
        this.primaryBorderPane.setPrefSize(1700, 700);

        this.top = new HBox();

        createArtistSelector();
        createAddStage();
        createsecondaryBorderPane();
    }

    public void createsecondaryBorderPane() {
        this.secondaryBorderPane = new BorderPane();
        this.primaryBorderPane.setCenter(this.secondaryBorderPane);

        this.stageBox.createStageBox(this.secondaryBorderPane, this.timetable);
        this.timeBox.createTimeBox(this.secondaryBorderPane);
    }

    public void createArtistSelector() {
        this.addArtist = new Button("add artist");
        this.addArtist.setOnMouseClicked(event -> {

            TextField artsitName = new TextField("artist's name");
            artsitName.setMinSize(90, 30);
            TextField artistPop = new TextField("artist's popularity");
            artistPop.setMinSize(90, 30);
            Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                data.Artist temporary = new data.Artist(artsitName.getText(), Integer.valueOf(artistPop.getText()));
                this.timetable.addArtist(temporary);
                this.secondaryStage.close();
                print();
            });
            confirm.setMinSize(90, 30);

            this.secondaryScene = new Scene(this.thirdBorderPane = new BorderPane());
            this.secondaryStage = new Stage();

            this.thirdBorderPane.setTop(artsitName);
            this.thirdBorderPane.setCenter(artistPop);
            this.thirdBorderPane.setBottom(confirm);

            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.setX(event.getX());
            this.secondaryStage.setY(event.getY());
            this.secondaryStage.show();
        });
        this.addArtist.setMinSize(105, 30);
        this.addArtist.setMaxSize(105, 30);
        this.top.getChildren().add(this.addArtist);
        this.primaryBorderPane.setTop(this.top);
    }

    public void createAddStage() {
        this.addArtist = new Button("add stage");
        this.addArtist.setOnMouseClicked(event -> {

            TextField stageName = new TextField("stage name");
            stageName.setMinSize(90, 30);
            TextField maxVisitors = new TextField("stage max size");
            maxVisitors.setMinSize(90, 30);
            Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                data.Stage temporary = new data.Stage(Integer.valueOf(maxVisitors.getText()), stageName.getText());
                this.timetable.addStage(temporary, this.stageBox);
                this.secondaryStage.close();
                print();
            });
            confirm.setMinSize(90, 30);

            this.secondaryScene = new Scene(this.thirdBorderPane = new BorderPane());
            this.secondaryStage = new Stage();

            this.thirdBorderPane.setTop(stageName);
            this.thirdBorderPane.setCenter(maxVisitors);
            this.thirdBorderPane.setBottom(confirm);

            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.setX(event.getX());
            this.secondaryStage.setY(event.getY());
            this.secondaryStage.show();
        });
        this.addArtist.setMinSize(105, 30);
        this.addArtist.setMaxSize(105, 30);
        this.top.getChildren().add(this.addArtist);
        this.primaryBorderPane.setTop(this.top);
    }

    public void print() {
        System.out.println(this.timetable.getArtists());
        System.out.println(this.timetable.getStages());
        for (data.Stage stage : this.timetable.getStages()) {
            System.out.println(stage.getPerformances());
        }
    }

}
