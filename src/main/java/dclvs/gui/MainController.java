package dclvs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    protected Stage stage;
    // historyAPI
    protected Scene backScene;
    protected Scene forwardScene;
    protected Scene currentScene;

    @FXML
    private Button backButton;

    @FXML
    private Button forwardButton;

    @FXML
    private ToggleGroup MatricesCalc;

    @FXML
    private RadioButton eigenProblemRadio;

    @FXML
    private RadioButton matricesOperationsRadio;

    @FXML
    private TitledPane matricesTiledPane;

    public void initialize() {
        setAllInitProperties();
        updateAllProperties();
    }

    public void setAllInitProperties() {
        this.backButton.setDisable(true);
        this.forwardButton.setDisable(true);

    }

    public void updateAllProperties() {
        switchButtonsState();
    }

    public void switchButtonsState() {
        System.out.println("in switch button");
        this.backButton.setDisable(this.backScene == null);
        System.out.println("1: " + this.backButton.isDisable());
        System.out.println(this.backScene);
        this.forwardButton.setDisable(this.forwardScene == null);
        System.out.println("2: " + this.forwardButton.isDisable());
        System.out.println(this.forwardScene + "\nend of switch");
    }

    public void switchMatricesOperationsScene(ActionEvent event) throws IOException {
        this.backScene = ((Node) event.getSource()).getScene();
        System.out.println(this.backScene + " " + this.forwardScene);
        switchButtonsState();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("Matrices/operationsScene.fxml")));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.currentScene = new Scene(root);
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void switchEigenProblemScene(ActionEvent event) throws IOException {
        this.backScene = ((Node) event.getSource()).getScene();
        System.out.println(this.backScene + " " + this.forwardScene);
        switchButtonsState();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("Matrices/eigenProblemScene.fxml")));
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.currentScene = new Scene(root);
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void backToTheFuture(ActionEvent event) {
        this.backScene = ((Node) event.getSource()).getScene();
        switchButtonsState();
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.currentScene = this.forwardScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

    public void forwardToThePast(ActionEvent event) {
        this.backScene = ((Node) event.getSource()).getScene();
        switchButtonsState();
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.currentScene = this.backScene;
        this.stage.setScene(this.currentScene);
        this.stage.show();
    }

}