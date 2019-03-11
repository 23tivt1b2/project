package gui.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class Intro {

    private Button line2 = new Button("start");

    public FlowPane start() {
        FlowPane startText = new FlowPane();
        Text line1 = new Text("press ");
        line2.setPadding(new Insets(0,0,0,0));
        line2.setAlignment(Pos.BOTTOM_CENTER);
        line2.getStyleClass().add("start-button");
        Text line3 = new Text(" to create a new planning.");
        startText.getChildren().addAll(line1,line2,line3);
        startText.setAlignment(Pos.CENTER);
        startText.getStyleClass().add("start-text");
        return startText;
    }
    public Button getStartbutton() {
        return line2;
    }
}
