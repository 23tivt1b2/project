package gui;

import gui.agenda.Timetable;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Random;


public class MainMenu extends Application {

    private String version = "0.1";

    private double x = 0;
    private double y = 0;
    
    private Color LIGHT_FILL_1 = Color.rgb(148,1,135);
    private Color LIGHT_FILL_2 = Color.rgb(148,1,130);
    private Color DARK_FILL_1 = Color.rgb(138,1,120);
    private Color DARK_FILL_2 = Color.rgb(138,1,115);
    private Color OFFWHITE = Color.rgb(235,235,235);
    private Color[] colors = {LIGHT_FILL_1, LIGHT_FILL_2, DARK_FILL_1,DARK_FILL_2};
    private int [] durations = {400, 500, 550, 600, 650};

    private boolean isFullscreen=false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("images/icon.png"));

        StackPane root = new StackPane();
        BackgroundFill backgroundFill = new BackgroundFill(OFFWHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        BorderPane container = new BorderPane();
        container.setBackground(background);

        Button minimize = new Button();
        Line minimizeGraphic = new Line();
        minimizeGraphic.setStartX(2.5f);
        minimizeGraphic.setStartY(7.5f);
        minimizeGraphic.setEndX(12.5f);
        minimizeGraphic.setEndY(7.5f);
        minimizeGraphic.setStroke(LIGHT_FILL_1);
        minimize.setGraphic(minimizeGraphic);
        minimize.setMinSize(30,30);
        minimize.getStyleClass().add("button");

        Button maximize = new Button();
        Rectangle maximizeIcon = new Rectangle();
        maximizeIcon.setFill(Color.TRANSPARENT);
        maximizeIcon.setStroke(LIGHT_FILL_1);
        maximizeIcon.setWidth(10.0f);
        maximizeIcon.setHeight(10.0f);

        maximize.setGraphic(maximizeIcon);
        maximize.setMinSize(30,30);
        maximize.getStyleClass().add("button");

        StackPane closeGraphic = new StackPane();
        Line x1 = new Line();
        x1.setStartX(2.5f);
        x1.setStartY(2.5f);
        x1.setEndX(12.5f);
        x1.setEndY(12.5f);
        x1.setStroke(LIGHT_FILL_1);
        Line x2 = new Line();
        x2.setStartX(2.5f);
        x2.setStartY(12.5f);
        x2.setEndX(12.5f);
        x2.setEndY(2.5f);
        x2.setStroke(LIGHT_FILL_1);
        closeGraphic.getChildren().addAll(x1,x2);
        Button close = new Button();
        close.setGraphic(closeGraphic);
        close.setMinSize(30,30);
        close.getStyleClass().add("button");

        HBox controlButtons = new HBox();
        controlButtons.getChildren().addAll(minimize,maximize,close);
        controlButtons.setAlignment(Pos.TOP_RIGHT);
        controlButtons.setPadding(new Insets(5,5,5,5));

        HBox versionBox = new HBox();
        Label versionLabel = new Label("version "+version);
        versionLabel.getStyleClass().add("version");
        versionBox.setAlignment(Pos.BOTTOM_RIGHT);
        versionLabel.setPadding(new Insets(5,10,0,10));
        versionBox.getChildren().add(versionLabel);

        StackPane containerRight = new StackPane();
        containerRight.getChildren().addAll(versionBox,controlButtons);
        StackPane.setAlignment(controlButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(versionBox,Pos.BOTTOM_RIGHT);
        container.setRight(containerRight);

        container.setLeft(menu());
        root.getChildren().addAll(container);

        Scene scene = new Scene(root,960, 540);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene.setOnMousePressed(mouseEvent -> {
            x = primaryStage.getX() - mouseEvent.getScreenX();
            y = primaryStage.getY() - mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() + x);
            primaryStage.setY(mouseEvent.getScreenY() + y);
        });
        close.setOnAction((event) -> {
            primaryStage.close();
        });
        maximize.setOnAction(event -> {
            if (isFullscreen) {
                primaryStage.setMaxWidth(960);
                primaryStage.setMaxHeight(540);
                primaryStage.setMaximized(false);
                isFullscreen=false;
            } else {
                primaryStage.setMaximized(true);
                isFullscreen=true;
            }
        });
        minimize.setOnAction((event -> {
            primaryStage.setIconified(true);
        }));
        primaryStage.show();
    }

    public StackPane menu() {
        StackPane menu = new StackPane();
        FlowPane menuBox = new FlowPane(Orientation.VERTICAL);

        GridPane background = new GridPane();
        Random rand = new Random();
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 8; col++) {
                int color1 = rand.nextInt(4);
                int color2 = rand.nextInt(4);
                int duration = rand.nextInt(5);
                Rectangle rec = new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                FillTransition ft = new FillTransition(Duration.millis(durations[duration]), rec, colors[color1], colors[color2]);
                ft.setCycleCount(6000);
                ft.setAutoReverse(true);
                ft.play();
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                background.getChildren().addAll(rec);
            }
        }

        StackPane logo = new StackPane();
        HBox logoBox = new HBox();

        Label festival = new Label("festival");
        festival.getStyleClass().add("festival");
        Label planner = new Label("planner");
        planner.getStyleClass().add("planner");
        logoBox.getChildren().addAll(festival,planner);

        Rectangle logoFill = new Rectangle();
        logoFill.setWidth(240);
        logoFill.setHeight(120);
        logoFill.setFill(Color.TRANSPARENT);

        logo.getChildren().addAll(logoFill,logoBox);
        logo.setPadding(new Insets(0,0,30,0));

        Button timeTable = new Button("TIMETABLE");
        timeTable.setMinSize(240,60);
        Timetable createTimetable = new Timetable();
        Button map = new Button("MAP");
        map.setMinSize(240,60);

        timeTable.setOnAction(event -> {
            try {
                createTimetable.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuBox.getChildren().addAll(logo,timeTable,map);

        menu.getChildren().addAll(background,menuBox);
        return menu;
    }

    public MainMenu() {

    }

    public static void main(String[] args) {
        launch(MainMenu.class);
    }
}
