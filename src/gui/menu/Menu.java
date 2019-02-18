package gui.menu;

import data.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Menu {

    private MenuBackground menuBackground = new MenuBackground();

    private StackPane menu = new StackPane();
    private VBox menuButtons = new VBox();

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

        menuBox.getChildren().addAll(logo,menuButtons);
        menu.getChildren().addAll(menuBackground.menuBackground(stageHeight),menuBox);
        return menu;
    }
    public void setFullscreen(double stageHeight){
        menu.getChildren().remove(menuBackground.menuBackground(stageHeight));
        menu.getChildren().add(0,menuBackground.menuBackground(stageHeight));
    }
    public void addButton(String name, int padding) {
        Button button = new Button(name);
        button.setMinSize(240,60);
        button.getStyleClass().add("map-buttons");
        button.setAlignment(Pos.BASELINE_LEFT);
        button.setPadding(new Insets(0,0,0,padding));
        button.setOnAction(event -> {
        });
        menuButtons.getChildren().add(button);
    }
    public Button getButton(int index) {
        return (Button)menuButtons.getChildren().get(index);
    }
}
