package dclvs.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.*;

import java.io.*;


public class CalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApp.class.getResource("welcomeWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Integer.MAX_VALUE, Integer.MAX_VALUE);
        primaryStage.setTitle("Numerical Methods Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}