package gui.agenda1;

import data.Performance;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;


public class Timetable {

    private data.Timetable timetable;

    private BorderPane primaryBorderPane;
    private BorderPane secondaryBorderPane;
    private BorderPane thirdBorderPane;

    private GridPane gridPane;

    private Stage primaryStage;
    private Stage secondaryStage;

    private Scene primaryScene;
    private Scene secondaryScene;

    private Button addArtist;
    private Button saveTimeTable;
    private Button loadTimeTable;

    private gui.agenda1.StageBox stageBox;
    private gui.agenda1.TimeBox timeBox;

    private HBox top;

    private Serializer serializer;


    public void start() throws Exception {

        this.stageBox = new gui.agenda1.StageBox();
        this.timeBox = new gui.agenda1.TimeBox();

        this.timetable = new data.Timetable();
        this.serializer = new Serializer();

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
        createSaveOption();
        loadSave();
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

            Label nameArtist = new Label("Name: ");
            nameArtist.setTextFill(Color.BLACK);
            TextField setArtist = new TextField();


            Label popularityArtist = new Label("Popularity: ");
            popularityArtist.setTextFill(Color.BLACK);
            TextField setPopularity = new TextField();

            Button save = new Button("Save");
            save.setStyle("-fx-background-color: white");
            save.setOnAction(event1 -> {
                data.Artist temporary = new data.Artist(setArtist.getText(), Integer.valueOf(setPopularity.getText()));
                this.timetable.addArtist(temporary);
                this.secondaryStage.close();
                print();
            });
            Button clear = new Button("Clear");
            clear.setStyle("-fx-background-color: white");
            clear.setOnAction(event1 -> {
                setArtist.clear();
                setPopularity.clear();
            });
            Button exit = new Button("Exit");
            exit.setStyle("-fx-background-color: white");
            exit.setOnAction(event1 -> {
               this.secondaryStage.close();
            });

            HBox buttons = new HBox();
            buttons.setSpacing(10);
            buttons.getChildren().addAll(save, clear, exit);

            this.secondaryScene = new Scene(this.gridPane = new GridPane(), 300, 130);
            this.secondaryStage.setOpacity(0.9);

            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage = new Stage();


            this.gridPane.setHgap(10);
            this.gridPane.setVgap(12);
            this.gridPane.add(nameArtist, 0, 1);
            this.gridPane.add(setArtist, 1, 1);
            this.gridPane.add(popularityArtist, 0, 2);
            this.gridPane.add(setPopularity, 1, 2);
            this.gridPane.add(buttons, 0 ,3, 3, 1);

            this.secondaryStage.setFullScreen(false);
            this.secondaryStage.setResizable(false);
            this.gridPane.setAlignment(Pos.BASELINE_CENTER);

            this.gridPane.setStyle(
                    "-fx-background-color: violet;"
                    + "-fx-background-radius: 8, 4;"
                    + "-fx-background-insets: 0;");

            this.secondaryStage.setOpacity(0.9);
            this.secondaryStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (! isNowFocused) {
                    this.secondaryStage.hide();
                }
            });

            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage.initStyle(StageStyle.TRANSPARENT);
            this.secondaryStage.setScene(this.secondaryScene);
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

            Label nameArtist = new Label("Name: ");
            nameArtist.setTextFill(Color.BLACK);
            TextField setStage = new TextField();

            Label popularityArtist = new Label("Capacity: ");
            popularityArtist.setTextFill(Color.BLACK);
            TextField setCapacity = new TextField();

            Button save = new Button("Save");
            save.setStyle("-fx-background-color: white");
            save.setOnAction(event1 -> {
                data.Stage temporary = new data.Stage(Integer.valueOf(setCapacity.getText()), setStage.getText());
                this.timetable.addStage(temporary, this.stageBox);
                this.secondaryStage.close();
                print();
            });
            Button clear = new Button("Clear");
            clear.setStyle("-fx-background-color: white");
            clear.setOnAction(event1 -> {
                setStage.clear();
                setCapacity.clear();
            });
            Button exit = new Button("Exit");
            exit.setStyle("-fx-background-color: white");
            exit.setOnAction(event1 -> {
                this.secondaryStage.close();
            });

            HBox buttons = new HBox();
            buttons.setSpacing(10);
            buttons.getChildren().addAll(save, clear, exit);

            this.secondaryScene = new Scene(this.gridPane = new GridPane(), 300, 130);
            this.secondaryStage.setOpacity(0.9);

            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage = new Stage();


            this.gridPane.setHgap(10);
            this.gridPane.setVgap(12);
            this.gridPane.add(nameArtist, 0, 1);
            this.gridPane.add(setStage, 1, 1);
            this.gridPane.add(popularityArtist, 0, 2);
            this.gridPane.add(setCapacity, 1, 2);
            this.gridPane.add(buttons, 0 ,3, 3, 1);

            this.secondaryStage.setFullScreen(false);
            this.secondaryStage.setResizable(false);
            this.gridPane.setAlignment(Pos.BASELINE_CENTER);

            this.gridPane.setStyle(
                    "-fx-background-color: violet;"
                            + "-fx-background-radius: 8, 4;"
                            + "-fx-background-insets: 0;");

            this.secondaryStage.setOpacity(0.9);
            this.secondaryStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (! isNowFocused) {
                    this.secondaryStage.hide();
                }
            });

            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage.initStyle(StageStyle.TRANSPARENT);
            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.show();

        });
        this.addArtist.setMinSize(105, 30);
        this.addArtist.setMaxSize(105, 30);
        this.top.getChildren().add(this.addArtist);
        this.primaryBorderPane.setTop(this.top);
    }

    public void createSaveOption() {
        this.saveTimeTable = new Button("Save TimeTable");
        this.saveTimeTable.setOnMouseClicked(event -> {

            TextField stageName = new TextField("TimeTable name");
            stageName.setMinSize(90, 30);
            Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                serializer.setTimeTable(this.timetable);
                serializer.serializeTimeTable(stageName.getText());
            });
            confirm.setMinSize(90, 30);

            BorderPane borderPaneSave = new BorderPane();
            this.secondaryScene = new Scene(borderPaneSave);
            this.secondaryStage = new Stage();

            borderPaneSave.setTop(stageName);
            borderPaneSave.setBottom(confirm);

            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.setX(event.getX());
            this.secondaryStage.setY(event.getY());
            this.secondaryStage.show();
        });

        this.top.getChildren().add(this.saveTimeTable);
    }

    public void loadSave() {
        this.loadTimeTable = new Button("Load file");
        this.loadTimeTable.setOnMouseClicked(event -> {
            TextField loadFile = new TextField("File name");

            loadFile.setMinSize(90, 30);Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                data.Timetable timetableTemp = this.serializer.deserializeTimeTable(loadFile.getText());
                if(timetableTemp != null) {
                    this.timetable = timetableTemp;
                    this.timetable.updateTimeTableInterface(stageBox);
                }
            });
            confirm.setMinSize(90, 30);

            BorderPane borderPaneSave = new BorderPane();
            this.secondaryScene = new Scene(borderPaneSave);
            this.secondaryStage = new Stage();

            borderPaneSave.setTop(loadFile);
            borderPaneSave.setBottom(confirm);

            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.setX(event.getX());
            this.secondaryStage.setY(event.getY());
            this.secondaryStage.show();
        });

        this.top.getChildren().add(this.loadTimeTable);
    }

    public void print() {
        System.out.println(this.timetable.getArtists());
        System.out.println(this.timetable.getStages());
        for (data.Stage stage : this.timetable.getStages()) {
            System.out.println(stage.getPerformances());
        }
    }

}
