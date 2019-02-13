package gui;

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
    private int [] durations = {4500, 5000, 5500, 6000, 6500};

    private boolean isFullscreen = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("images/icon.png"));

        StackPane root = new StackPane();
        BackgroundFill backgroundFill = new BackgroundFill(OFFWHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        BorderPane container = new BorderPane();
        container.setBackground(background);

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

        StackPane containerRight = new StackPane();
        containerRight.getChildren().addAll(versionBox,controlButtons);
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
        container.setCenter(startText);
        container.setLeft(menu());
        container.setRight(containerRight);
        root.getChildren().addAll(container);

        Scene scene = new Scene(root,960, 540);
        scene.getStylesheets().add("css/styles.css");
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
                maximize.setGraphic(maximizeIcon);
            } else {
                primaryStage.setMaximized(true);
                isFullscreen=true;
                maximize.setGraphic(restoreIcon);
            }
        });
        minimize.setOnAction((event -> {
            primaryStage.setIconified(true);
        }));
        primaryStage.show();
    }

    public GridPane menuBackground() {
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
        return background;
    }

    public StackPane menu() throws FileNotFoundException {
        StackPane menu = new StackPane();
        FlowPane menuBox = new FlowPane(Orientation.VERTICAL);
        StackPane logo = new StackPane();
        HBox logoBox = new HBox();

        Image logoImage = new Image(new FileInputStream("recources/images/logo.png"));
        ImageView logoImageView = new ImageView(logoImage);

        Rectangle logoFill = new Rectangle();
        logoFill.setWidth(240);
        logoFill.setHeight(150);
        logoFill.setFill(Color.TRANSPARENT);

        logo.getChildren().addAll(logoFill,logoImageView);
        logo.setAlignment(Pos.CENTER);

        Button timeTable = new Button("timetable");
        timeTable.setMinSize(240,60);
        timeTable.getStyleClass().add("map-buttons");
        timeTable.setAlignment(Pos.BASELINE_LEFT);
        timeTable.setPadding(new Insets(0,0,0,30));
        Button map = new Button("map");
        map.setMinSize(240,60);
        map.getStyleClass().add("map-buttons");
        map.setAlignment(Pos.BASELINE_LEFT);
        map.setPadding(new Insets(0,0,0,27));
        Button help = new Button("help");
        help.setMinSize(240,60);
        help.getStyleClass().add("map-buttons");
        help.setAlignment(Pos.BASELINE_LEFT);
        help.setPadding(new Insets(0,0,0,27));

        menuBox.getChildren().addAll(logo,timeTable,map,help);
        menu.getChildren().addAll(menuBackground(),menuBox);
        return menu;
    }

    public static void main(String[] args) {
        launch(MainMenu.class);
    }
}
