package gui;

import data.Artist;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalTime;


public class Timetable {

    private data.Timetable timetable;

    private BorderPane mainBorderPane;
    private BorderPane secondaryBorderPane;

    private data.Stage stage;

    private Stage secondaryStage;

    private GridPane agenda;

    private ComboBox<Artist> selectBand;

    private Artist selectedArtist;

    private Scene addArtistScene;

    private LocalTime temoraryLocalTime;

    public void start() throws Exception {

        this.timetable = new data.Timetable();

        this.mainBorderPane = new BorderPane();

        this.stage = new data.Stage();
        this.secondaryStage = new Stage();

        Stage mainStage = new Stage();
        Scene mainScene = new Scene(this.mainBorderPane);
        mainStage.setScene(mainScene);

        this.mainBorderPane.autosize();
        this.mainBorderPane.setPrefSize(1700, 700);

        createTop(this.mainBorderPane);
        createStageBox(this.mainBorderPane);
        createSelcetBand();
        createaddArtist();

        createSecondaryBorderPane(this.mainBorderPane);

        mainStage.show();

    }

    public void createSecondaryBorderPane(BorderPane mainBorderPane) {
        this.secondaryBorderPane = new BorderPane();

        mainBorderPane.setCenter(this.secondaryBorderPane);
        createTimeBox(this.secondaryBorderPane);
        createGridPane();

    }

    private void createGridPane() {
        HBox agendaBoss = new HBox();
        this.agenda = new GridPane();
        agendaBoss.getChildren().add(this.agenda);
        this.secondaryBorderPane.setCenter(agendaBoss);

        int i = 0;
        for (data.Stage stage : this.timetable.getStages()) {
            i++;
            for (int k = 0; k < 25; k++) {
                Button test = new Button();
                test.setMaxSize(60, 30);
                test.setMinSize(60, 30);
                test.setOpacity(0.5);
                final int h = i;
                final int q = k;
                test.setOnMouseClicked(event -> {
                    test.setStyle("-fx-background-color: #0000FF;");
                    //this.timetable.getStages().get(h + 1).addPerfomance(new Performance(this.selectBand.getValue(), this.temoraryLocalTime = new LocalTime(q+1,0,0,0)));
                });
                //TODO als knop wordt ingedrukt moet er een performance komen te staan.
                this.agenda.add(test, k, i);
            }
        }
    }

    public void createSelcetBand() {
        this.selectBand = new ComboBox<>();
        this.selectBand.setOpacity(1);
        this.selectBand.setMinSize(60, 30);
        this.mainBorderPane.setRight(this.selectBand);

    }

    public void createaddArtist() {
        Button addArtist = new Button("Add artist");
        addArtist.setMinSize(60, 30);
        this.mainBorderPane.setTop(addArtist);
        BorderPane addArtistPane = new BorderPane();
        this.addArtistScene = new Scene(addArtistPane);

        addArtist.setOnMouseClicked(event -> {

            TextField popularity = new TextField("Popularity");
            TextField artistName = new TextField("Name");
            addArtistPane.getChildren().clear();
            addArtistPane.setTop(artistName);
            addArtistPane.setCenter(popularity);
            Button confirm = new Button("confirm");
            addArtistPane.setBottom(confirm);
            confirm.setOnMouseClicked(e -> {
                Artist test = new Artist(artistName.getText(), Integer.valueOf(popularity.getText()));
                this.selectBand.getItems().add(test);
                this.selectedArtist = test;
                this.secondaryStage.close();
            });
            this.secondaryStage.setScene(addArtistScene);
            this.secondaryStage.show();
        });
    }

    public void createTop(BorderPane mainBorderPane) {
        HBox top = new HBox();
        top.setMaxHeight(30);
        mainBorderPane.setTop(top);

    }

    public void createTimeBox(BorderPane secondaryBorderPane) {
        HBox timeBox = new HBox();
        timeBox.setMaxHeight(30);

        for (int i = 0; i < 25; i++) {
            TextField hoi = new TextField("" + i + ":00");
            hoi.setMaxHeight(30);
            hoi.setMaxWidth(60);
            hoi.setMinHeight(30);
            hoi.setMinWidth(60);
            hoi.setDisable(true);
            hoi.setOpacity(1);
            timeBox.getChildren().add(hoi);
        }

        secondaryBorderPane.setTop(timeBox);
    }

    public void createAddStage(VBox left) {
        Button addStage = new Button("add stage");
        addStage.setMinSize(90, 30);
        left.getChildren().add(addStage);
        addStage.setOnAction(event -> {
            addStage();
        });
    }

    public void createStageBox(BorderPane borderPane) {
        VBox stageBox = new VBox();
        stageBox.setMaxWidth(90);
        stageBox.setMinWidth(30);
        borderPane.setLeft(stageBox);

        TextField fill = new TextField("Stage / Time");
        fill.setDisable(true);
        fill.setOpacity(1);
        fill.setMinHeight(30);

        stageBox.getChildren().add(fill);

        for (data.Stage stage : this.timetable.getStages()) {
            Button test = new Button(stage.getName());
            test.setMinSize(90, 30);
            test.setMaxSize(90, 30);
            stageBox.getChildren().add(test);

            test.setOnMouseClicked(event -> {
                VBox stageOptions = new VBox();
                Scene stageOptionScene = new Scene(stageOptions);
                this.secondaryStage.setScene(stageOptionScene);

                this.secondaryStage.setX(event.getX());
                this.secondaryStage.setY(event.getX());

                Button removeStage = new Button("Remove this stage.");
                removeStage.setMaxWidth(360);
                Button changeSize = new Button("Change the maximum amount of visitor's of this stage.");
                changeSize.setMinWidth(360);
                Button chageName = new Button("Change the name of this stage.");
                chageName.setMinWidth(360);

                // TODO: 12-2-2019 Er moer nog fucionaliteit achter deze knoppen geplaats worden.

                stageOptions.getChildren().addAll(removeStage, changeSize, chageName);
                this.secondaryStage.setResizable(false);
                this.secondaryStage.show();
            });
        }

        createAddStage(stageBox);
    }

    public void addStage() {
        HBox addPane = new HBox();

        Scene addScene = new Scene(addPane);
        Stage addStage = new Stage();
        addStage.setScene(addScene);

        TextField stageName = new TextField("Stage name");
        TextField stageSize = new TextField("Max visitor's");
        Button confirm = new Button("Confirm");

        addPane.getChildren().add(stageName);
        addPane.getChildren().add(stageSize);
        addPane.getChildren().add(confirm);

        addStage.show();

        confirm.setOnAction(event -> {
            //data.Stage test = new data.Stage(5, "hoi");

            this.timetable.addStage(new data.Stage(Integer.valueOf(stageSize.getText()), stageName.getText()));
            this.mainBorderPane.setLeft(null);
            createStageBox(this.mainBorderPane);
            createGridPane();
            addStage.close();
        });
    }

}
