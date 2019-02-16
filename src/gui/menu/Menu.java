package gui.menu;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Menu {

    private MenuBackground menuBackground = new MenuBackground();

    private StackPane menu = new StackPane();

    public StackPane menu(double stageHeight) throws FileNotFoundException {

        FlowPane menuBox = new FlowPane(Orientation.VERTICAL);
        StackPane logo = new StackPane();

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
        menu.getChildren().addAll(menuBackground.menuBackground(stageHeight),menuBox);
        return menu;
    }
    public void setFullscreen(double stageHeight){
        menu.getChildren().remove(menuBackground.menuBackground(stageHeight));
        menu.getChildren().add(0,menuBackground.menuBackground(stageHeight));
    }
}
