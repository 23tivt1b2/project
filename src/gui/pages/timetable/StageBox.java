package gui.pages.timetable;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageBox {

    private VBox stageBox;

    private Button addArtist;

    private Scene primaryScene;
    private Stage primaryStage;

    private BorderPane secondaryBorderPane;

    private TextField artistName;
    private TextField artistPop;
    private Button confirm;
    private VBox timeLines;

    public void createStageBox(BorderPane secondaryBorderPane, data.Timetable timetable) {
        this.secondaryBorderPane = new BorderPane();
        this.primaryScene = new Scene(this.secondaryBorderPane);
        this.primaryStage = new Stage();

        this.stageBox = new VBox();
        this.stageBox.setMinSize(90, 30);
        this.timeLines = new VBox();
        secondaryBorderPane.setLeft(this.stageBox);
        secondaryBorderPane.setCenter(this.timeLines);

        this.stageBox.getChildren().clear();
        for (data.Stage stage : timetable.getStages()) {
            Button podium = new Button(stage.getStageName());
            this.stageBox.getChildren().add(podium);
        }
    }

    public void createAddArtistButton(data.Timetable timetable, HBox top) {
        this.artistName = new TextField("artist's name");
        this.artistPop = new TextField("artist's popularity");
        this.confirm = new Button("confirm");
        this.secondaryBorderPane = new BorderPane();
        this.primaryScene = new Scene(this.secondaryBorderPane);
        this.primaryStage = new Stage();
        this.addArtist = new Button("add stage");
        this.addArtist.setMinSize(105, 30);

        this.secondaryBorderPane.setTop(this.artistName);
        this.secondaryBorderPane.setCenter(this.artistPop);
        this.secondaryBorderPane.setBottom(this.confirm);
        this.primaryStage.setScene(this.primaryScene);

        this.addArtist.setOnMouseClicked(event -> {
            this.primaryStage.show();
        });

        this.confirm.setOnMouseClicked(event -> {
            data.Stage temporary = new data.Stage(Integer.valueOf(artistPop.getText()), this.artistName.getText());
            timetable.getStages().add(temporary);
            Button temporary2 = new Button(temporary.toString());
            temporary2.setOnMouseClicked(event1 -> {
                stageOptions(event1.getX(), event1.getY(), temporary);
            });
            temporary2.setMinSize(90, 30);
            temporary2.setMaxSize(90, 30);
            this.stageBox.getChildren().add(temporary2);
            this.primaryStage.close();
            gui.pages.timetable.TimeLine temporary3 = new TimeLine(temporary, this.timeLines);
        });

        top.getChildren().add(this.addArtist);
    }

    public void stageOptions(double x, double y, data.Stage stage) {
        this.secondaryBorderPane.getChildren().clear();

        VBox stageOptions = new VBox();

        Button remove = new Button("remove");
        Button addPerformance = new Button("add performance");
        addPerformance.setOnMouseClicked(event -> {
            addPerformance(stage, event.getX(), event.getY());
        });
        Button changeMaxVisitors = new Button("change max visitors");
        Button changeName = new Button("change name");

        stageOptions.getChildren().addAll(remove, addPerformance, changeMaxVisitors, changeName);

        this.secondaryBorderPane.setCenter(stageOptions);

        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.setY(y);
        this.primaryStage.setX(x);
        this.primaryStage.show();
    }

    public void addPerformance(data.Stage stage, double x, double y) {
        this.secondaryBorderPane.getChildren().clear();

        VBox stageOptions = new VBox();



        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.setY(y);
        this.primaryStage.setX(x);
        this.primaryStage.show();
    }
}
