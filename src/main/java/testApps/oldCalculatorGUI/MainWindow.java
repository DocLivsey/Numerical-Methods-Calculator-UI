package testApps.oldCalculatorGUI;

import dclvs.gui.CalculatorApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class MainWindow {
    public static void setMainWindow(TestApp app) throws IOException {
        app.previousScene = app.currentScene;
        app.currentScene = new Scene(CombineWindow(StayWithUs(app), MainMenu(app)), Double.MAX_VALUE, Double.MAX_VALUE);
        app.primaryStage.setScene(app.currentScene);
    }
    public static StackPane CombineWindow(Node... nodes) {
        return new StackPane(nodes);
    }
    public static HBox MainMenu(TestApp app) {
        Menu matrixMenu = new Menu("Matrix calc");
        MenuItem solving = new MenuItem("Solving Linear System");
        MenuItem operations = new MenuItem("Matrices operations");
        MenuItem eigenValue = new MenuItem("Finding eigen pairs");
        matrixMenu.getItems().addAll(List.of(new MenuItem("Solving Linear System"),
                new MenuItem("Finding eigen pairs"), new MenuItem("Matrices operations")));
        EventHandler<ActionEvent> handler = getActionEventEventHandler(app);
        solving.setOnAction(handler);
        operations.setOnAction(handler);
        eigenValue.setOnAction(handler);
        MenuBar menuBar = new MenuBar(matrixMenu);
        HBox hBox = new HBox(menuBar);
        hBox.setAlignment(Pos.TOP_LEFT);
        return hBox;
    }

    public static EventHandler<ActionEvent> getActionEventEventHandler(TestApp app) {
        StackPane pane = new StackPane();
        return event -> {
            app.previousScene = app.currentScene;
            if (((MenuItem)event.getSource()).getText().equals("Solving Linear System")) {
                Label label = new Label("That`s linear solving");
                pane.getChildren().add(label);
                app.currentScene = new Scene(pane, 200, 100);
            } else if (((MenuItem)event.getSource()).getText().equals("Matrices operations")) {
                Label label = new Label("That`s Matrices operations");
                pane.getChildren().add(label);
                app.currentScene = new Scene(pane, 200, 100);
            } else {
                Label lbl = new Label("That`s Finding eigen pairs");
                pane.getChildren().add(lbl);
                app.currentScene = new Scene(pane, 200, 100);
            }
            app.primaryStage.setScene(app.currentScene);
        };
    }

    public static GridPane StayWithUs(TestApp app) throws IOException {
        GridPane gridPane = new GridPane();
        Button button = new Button("Change");
        button.setOnAction(actionEvent -> {
            System.out.println("change");
            app.previousScene = app.currentScene;
            app.currentScene = new Scene(new StackPane(new Label("New")), 300, 200);
            app.primaryStage.setScene(app.currentScene);
        });
        /*Image image = new Image(new FileInputStream("src/main/resources/images/" +
                "welcome_window/istockphoto-1364326606-170667a.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(200);
        imageView.setY(100);
        gridPane.getChildren().add(imageView);*/
        gridPane.getChildren().add(button);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
}
class TestApp extends Application {
    public Stage primaryStage;
    public Scene currentScene;
    public Scene previousScene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        testWindow(this.primaryStage);
    }

    public void testWindow(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        MainWindow.setMainWindow(this);
        this.primaryStage.setTitle("Calculator");
        this.primaryStage.show();
    }
}
