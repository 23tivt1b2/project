package gui;

import data.Artist;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Timetable {

    private data.Timetable timetable;

    private BorderPane primaryBorderPane;
    private BorderPane secondaryBorderPane;

    private Stage primaryStage;
    private Stage secondaryStage;

    private Scene primaryScene;
    private Scene secondaryScene;

    private ComboBox<Artist> artistSelector;

    private VBox stageBox;
    private HBox timeBox;

    private Button addArtist;


    public void start() throws Exception {
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

        createArtistSelector();
        createsecondaryBorderPane();
    }

    public void createArtistSelector() {
        this.artistSelector = new ComboBox<>();
        this.primaryBorderPane.setTop(this.artistSelector);
    }

    public void createsecondaryBorderPane() {
        this.secondaryBorderPane = new BorderPane();
        this.primaryBorderPane.setCenter(this.secondaryBorderPane);

        createStageBox();
        createTimeBox();
    }

    public void createStageBox() {
        this.stageBox = new VBox();
        this.stageBox.setMinSize(90, 30);
        this.secondaryBorderPane.setLeft(this.stageBox);

        this.stageBox.getChildren().clear();
        for (data.Stage stage : this.timetable.getStages()) {
            Button podium = new Button(stage.getName());
            this.stageBox.getChildren().add(podium);
        }

        createAddArtistButton();
    }

    public void createAddArtistButton() {
        this.addArtist = new Button("add artist");
        this.addArtist.setMinSize(90, 30);
        this.stageBox.getChildren().add(this.addArtist);
    }

    public void createTimeBox() {
        this.timeBox = new HBox();
        this.timeBox.setMinSize(60, 30);
        this.secondaryBorderPane.setTop(this.timeBox);
    }

}
