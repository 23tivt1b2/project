package gui;

import gui.menu.Menu;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application {

    private String version = "0.1";

    private double x = 0;
    private double y = 0;
    
    private Color LIGHT_FILL_1 = Color.rgb(148,1,135);
    private Color OFF_WHITE = Color.rgb(235,235,235);

    private boolean isFullscreen = false;

    private double stageHeight = 540;
    private double stageWidth = 960;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("images/icon.png"));


        BackgroundFill backgroundFill = new BackgroundFill(OFF_WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        BorderPane root = new BorderPane();
        root.setBackground(background);

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

        StackPane rootRight = new StackPane();
        rootRight.getChildren().addAll(versionBox,controlButtons);
        StackPane.setAlignment(controlButtons,Pos.TOP_RIGHT);
        StackPane.setAlignment(versionBox,Pos.BOTTOM_RIGHT);

        FlowPane startText = new FlowPane();
        Text line1 = new Text("press ");
        Button line2 = new Button("start");
        line2.setPadding(new Insets(10,0,0,0));
        line2.setAlignment(Pos.BOTTOM_CENTER);
        Text line3 = new Text(" to create a new planning.");
        startText.getChildren().addAll(line1,line2,line3);
        startText.setAlignment(Pos.CENTER);
        startText.getStyleClass().add("start-text");

        root.setCenter(startText);
        Menu menu = new Menu();
        root.setLeft(menu.menu(stageHeight));
        root.setRight(rootRight);

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

    public static void main(String[] args) {
        launch(Main.class);
    }
}
