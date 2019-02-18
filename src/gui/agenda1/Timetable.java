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
    private Button addStage;
    private Button saveTimeTable;
    private Button loadTimeTable;

    private gui.agenda1.StageBox stageBox;
    private gui.agenda1.TimeBox timeBox;

    private HBox top;

    private Serializer serializer;

    private final String IDLE_BUTTON_STYLE = "-fx-border-color: #8E9FBB;" +
            " -fx-border-width: 3px;"
            + "-fx-background-color: #8E9FBB";
    private final String HOVERED_BUTTON_STYLE = "-fx-background-color: transparent;";


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
        this.addArtist.setStyle(this.IDLE_BUTTON_STYLE);
        this.addArtist.setOnMouseEntered(e -> this.addArtist.setStyle(this.HOVERED_BUTTON_STYLE));
        this.addArtist.setOnMouseExited(e -> this.addArtist .setStyle(this.IDLE_BUTTON_STYLE));
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
                if (!this.timetable.getArtistsName().contains(setArtist.getText())) {
                    data.Artist temporary = new data.Artist(setArtist.getText(), Integer.valueOf(setPopularity.getText()));
                    this.timetable.addArtist(temporary);
                    this.secondaryStage.close();
                    print();
                } else {
                    setArtist.clear();
                    setArtist.setPromptText("//artist already exists");
                }
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
                    "-fx-background-color: CACFE2;"
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


        this.addArtist.setMinSize(125, 60);
        this.addArtist.setMaxSize(125, 60);
        this.top.getChildren().add(this.addArtist);
        this.primaryBorderPane.setTop(this.top);
    }

    public void createAddStage() {
        this.addStage = new Button("add stage");
        this.addStage.setStyle(IDLE_BUTTON_STYLE);
        this.addStage.setOnMouseEntered(e -> this.addStage.setStyle(this.HOVERED_BUTTON_STYLE));
        this.addStage.setOnMouseExited(e -> this.addStage.setStyle(this.IDLE_BUTTON_STYLE));
        this.addStage.setOnMouseClicked(event -> {

            Label nameArtist = new Label("Name: ");
            nameArtist.setTextFill(Color.BLACK);
            TextField setStage = new TextField();

            Label popularityArtist = new Label("Capacity: ");
            popularityArtist.setTextFill(Color.BLACK);
            TextField setCapacity = new TextField();

            Button save = new Button("Save");
            save.setStyle("-fx-background-color: white");
            save.setOnAction(event1 -> {
                if (!this.timetable.getStageNames().contains(setStage.getText())) {
                    data.Stage temporary = new data.Stage(Integer.valueOf(setCapacity.getText()), setStage.getText());
                    this.timetable.addStage(temporary, this.stageBox);
                    this.secondaryStage.close();
                    print();
                } else {
                    setStage.clear();
                    setStage.setPromptText("//Stage already exists");
                }
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
                    "-fx-background-color: CACFE2;"
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
        this.addStage.setMinSize(125, 60);
        this.addStage.setMaxSize(125, 60);
        this.top.getChildren().add(this.addStage);
        this.primaryBorderPane.setTop(this.top);
    }

    public void createSaveOption() {
        this.saveTimeTable = new Button("Save TimeTable");
        this.saveTimeTable.setStyle(IDLE_BUTTON_STYLE);
        this.saveTimeTable.setOnMouseEntered(e -> this.saveTimeTable.setStyle(this.HOVERED_BUTTON_STYLE));
        this.saveTimeTable.setOnMouseExited(e -> this.saveTimeTable.setStyle(this.IDLE_BUTTON_STYLE));
        this.saveTimeTable.setOnMouseClicked(event -> {

            TextField stageName = new TextField();
            stageName.clear();
            stageName.setPromptText("//Name time table");
            stageName.setMinSize(90, 30);
            Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                if (stageName.getText().length() > 0) {
                    serializer.setTimeTable(this.timetable);
                    serializer.serializeTimeTable(stageName.getText());
                    this.secondaryStage.close();
                } else {
                    stageName.setPromptText("//name necessary");
                }
            });
            confirm.setMinSize(90, 30);

            this.secondaryScene = new Scene(this.gridPane = new GridPane(), 300, 100);

            this.gridPane.setHgap(10);
            this.gridPane.setVgap(12);
            this.gridPane.add(stageName, 0, 1);
            this.gridPane.add(confirm, 0, 2);
            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage = new Stage();

            this.secondaryStage.setFullScreen(false);
            this.secondaryStage.setResizable(false);
            this.gridPane.setAlignment(Pos.BASELINE_CENTER);

            this.gridPane.setStyle(
                    "-fx-background-color: CACFE2;"
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

        this.saveTimeTable.setMinSize(125, 60);
        this.saveTimeTable.setMaxSize(125, 60);
        this.top.getChildren().add(this.saveTimeTable);
    }

    public void loadSave() {
        this.loadTimeTable = new Button("Load file");
        this.loadTimeTable.setStyle(IDLE_BUTTON_STYLE);
        this.loadTimeTable.setOnMouseEntered(e -> this.loadTimeTable.setStyle(this.HOVERED_BUTTON_STYLE));
        this.loadTimeTable.setOnMouseExited(e -> this.loadTimeTable.setStyle(this.IDLE_BUTTON_STYLE));
        this.loadTimeTable.setOnMouseClicked(event -> {
            TextField loadTemp = new TextField();
            loadTemp.setMinSize(90, 30);
            Button confirm = new Button("confirm");
            confirm.setOnMouseClicked(event1 -> {
                data.Timetable timetableTemp = this.serializer.deserializeTimeTable(loadTemp.getText());
                if (timetableTemp != null) {
                    this.timetable = timetableTemp;
                    this.timetable.updateTimeTableInterface(stageBox);
                }
                this.secondaryStage.close();
            });
            confirm.setMinSize(90, 30);

            this.secondaryScene = new Scene(this.gridPane = new GridPane(), 300, 100);

            this.gridPane.setHgap(10);
            this.gridPane.setVgap(12);
            this.gridPane.add(loadTemp, 0, 1);
            this.gridPane.add(confirm, 0, 2);
            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage = new Stage();

            this.secondaryStage.setFullScreen(false);
            this.secondaryStage.setResizable(false);
            this.gridPane.setAlignment(Pos.BASELINE_CENTER);

            this.gridPane.setStyle(
                    "-fx-background-color: CACFE2;"
                            + "-fx-background-radius: 8, 4;"
                            + "-fx-background-insets: 0;");

            this.secondaryStage.setOpacity(0.9);
            this.secondaryStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    this.secondaryStage.hide();
                }
            });
            this.secondaryScene.setFill(Color.TRANSPARENT);
            this.secondaryStage.initStyle(StageStyle.TRANSPARENT);
            this.secondaryStage.setScene(this.secondaryScene);
            this.secondaryStage.show();
        });

        this.loadTimeTable.setMinSize(125, 60);
        this.loadTimeTable.setMaxSize(125, 60);
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
