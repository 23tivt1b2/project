package gui;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.event.ActionEvent;


public class MainMenu extends Application {
    private double x = 0;
    private double y = 0;
    private Color FILL_1 = Color.rgb(191,137,244);
    private Color FILL_2 = Color.rgb(163,109,246);
    private Color FILL_3 = Color.rgb(163,109,217);

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane menu = new StackPane();
        BorderPane menuField = new BorderPane();
        FlowPane menuBox = new FlowPane(Orientation.VERTICAL);

        Rectangle background = new Rectangle();
        background.setWidth(960);
        background.setHeight(540);
        FillTransition ft = new FillTransition(Duration.millis(6000), background, FILL_1, Color.RED);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        ft.play();

        StackPane logoPane = new StackPane();
        Label logo = new Label("LOGO");
        Rectangle logoFill = new Rectangle();
        logoFill.setWidth(360);
        logoFill.setHeight(120);
        logoFill.setFill(Color.WHITE);
        logoPane.getChildren().addAll(logoFill,logo);
        Button timeTable = new Button("TIMETABLE");
        timeTable.setMinSize(360,60);
        Button map = new Button("MAP");
        map.setMinSize(360,60);
        Button exit = new Button("EXIT");

        exit.setMinSize(360,60);


        menuBox.getChildren().addAll(logoPane,timeTable,map,exit);
        menuBox.setAlignment(Pos.CENTER);
        menuField.setCenter(menuBox);
        menu.getChildren().addAll(background,menuField);
        Scene scene = new Scene(menu,960, 540);
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
        exit.setOnAction((event) -> {
            primaryStage.close();
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(MainMenu.class);
    }
}
