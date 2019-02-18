package gui;

import gui.menu.Menu;
import gui.pages.Intro;
import gui.pages.timetable.Timetable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Root extends Application {

    private String version = "0.2";

    private double x = 0;
    private double y = 0;
    
    private Color LIGHT_FILL_1 = Color.rgb(148,1,135);
    private Color OFF_WHITE = Color.rgb(235,235,235);
    private Color OFF_BLACK = Color.rgb(15,15,15);

    private boolean isFullscreen = false;

    private double stageHeight = 540;
    private double stageWidth = 960;

    private BorderPane root = new BorderPane();
    private BorderPane container = new BorderPane();
    private Menu menu = new Menu();
    private Timetable timetable = new Timetable();
    private Intro main = new Intro();

    public void setButtons() {
        menu.addButton("timetable",30);
        menu.getButton(0).setOnAction(event -> {
            try {
                container.setCenter(timetable.start());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menu.addButton("map",27);
        menu.getButton(1).setOnAction(event -> {
            try {
                container.setCenter(main.start());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menu.addButton("help",27);
        main.getStartbutton().setOnAction(event -> {
            try {
                container.setCenter(timetable.start());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("images/icon.png"));

        BackgroundFill backgroundFill = new BackgroundFill(OFF_WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        root.setBackground(background);

        Scene scene = new Scene(root,stageWidth, stageHeight);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene.setOnMousePressed(mouseEvent -> {
            x = primaryStage.getX() - mouseEvent.getScreenX();
            y = primaryStage.getY() - mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() + x);
            primaryStage.setY(mouseEvent.getScreenY() + y);
        });
        Button minimize = new Button();
        Line minimizeIcon = new Line();
        minimizeIcon.setStartX(2.5f);
        minimizeIcon.setStartY(7.5f);
        minimizeIcon.setEndX(12.5f);
        minimizeIcon.setEndY(7.5f);
        minimizeIcon.setStroke(LIGHT_FILL_1);
        minimize.setGraphic(minimizeIcon);
        minimize.setMinSize(30,30);
        minimize.getStyleClass().add("control-buttons");

        Button maximize = new Button();
        Rectangle maximizeIcon = new Rectangle();
        maximizeIcon.setFill(Color.TRANSPARENT);
        maximizeIcon.setStroke(LIGHT_FILL_1);
        maximizeIcon.setWidth(10.0f);
        maximizeIcon.setHeight(10.0f);

        SVGPath restoreIcon = new SVGPath();
        restoreIcon.setContent("m 10 12 h 8 v 8 h -8 v -8 m 2 0 v -2 h 8 v 8 h -2");
        restoreIcon.setStroke(LIGHT_FILL_1);
        restoreIcon.setSmooth(false);
        restoreIcon.setFill(Color.TRANSPARENT);

        maximize.setGraphic(maximizeIcon);
        maximize.setMinSize(30,30);
        maximize.getStyleClass().add("control-buttons");

        SVGPath closeIcon = new SVGPath();
        closeIcon.setContent("m 11 11 l 9 9 m -9 0 l 9 -9");
        closeIcon.setStroke(LIGHT_FILL_1);
        Button close = new Button();
        close.setGraphic(closeIcon);
        close.setMinSize(30,30);
        close.getStyleClass().add("control-buttons");

        HBox controlButtons = new HBox();
        controlButtons.getChildren().addAll(minimize,maximize,close);
        controlButtons.setAlignment(Pos.TOP_RIGHT);

        HBox versionBox = new HBox();
        Label versionLabel = new Label("version "+version);
        versionLabel.getStyleClass().add("version");
        versionBox.setAlignment(Pos.BOTTOM_RIGHT);
        versionLabel.setPadding(new Insets(5,10,0,10));
        versionBox.getChildren().add(versionLabel);

        setButtons();

        root.setLeft(this.menu.menu(stageHeight));
        container.setCenter(main.start());
        container.setTop(controlButtons);
        container.setBottom(versionBox);
        root.setCenter(container);
        close.setOnAction((event) -> {
            primaryStage.close();
        });
        maximize.setOnAction(event -> {
            if (isFullscreen) {
                primaryStage.setMaxWidth(stageWidth);
                primaryStage.setMaxHeight(stageHeight);
                primaryStage.setMaximized(false);
                isFullscreen=false;
                maximize.setGraphic(maximizeIcon);
                menu.setFullscreen(stageHeight);
            } else {
                primaryStage.setMaximized(true);
                isFullscreen=true;
                maximize.setGraphic(restoreIcon);
                stageHeight = primaryStage.getHeight();
                menu.setFullscreen(stageHeight);
            }
        });
        minimize.setOnAction((event -> {
            primaryStage.setIconified(true);
        }));
        primaryStage.show();
    }
}
