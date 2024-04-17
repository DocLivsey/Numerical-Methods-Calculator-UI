package dclvs.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.*;
import java.util.List;


public class CalculatorApp extends Application {
    protected Stage primaryStage;
    protected Scene currentScene;
    protected Scene previousScene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Integer.MAX_VALUE, Integer.MAX_VALUE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    public void testWindow(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        MainWindow.setMainWindow(this);
        this.primaryStage.setTitle("Calculator");
        this.primaryStage.show();
    }
}