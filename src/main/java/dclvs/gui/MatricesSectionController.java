package dclvs.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MatricesSectionController extends MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private HBox topHBox;

    @FXML
    private ToggleGroup MatricesCalc;

    @FXML
    private Button backButton;

    @FXML
    private Button decreaseMatrixColumnButton;

    @FXML
    private Button decreaseMatrixRowButton;

    @FXML
    private Button decreaseFirstMatrixColumnButton;

    @FXML
    private Button decreaseFirstMatrixRowButton;

    @FXML
    private Button decreaseSecondMatrixColumnButton;

    @FXML
    private Button decreaseSecondMatrixRowButton;

    @FXML
    private RadioButton eigenProblemRadio;

    @FXML
    private Button forwardButton;

    @FXML
    private Button increaseMatrixColumnButton;

    @FXML
    private Button increaseMatrixRowButton;

    @FXML
    private Button increaseFirstMatrixColumnButton;

    @FXML
    private Button increaseFirstMatrixRowButton;

    @FXML
    private Button increaseSecondMatrixColumnButton;

    @FXML
    private Button increaseSecondMatrixRowButton;

    @FXML
    private RadioButton matricesOperationsRadio;

    @FXML
    private TitledPane matricesTiledPane;

    @FXML
    private GridPane matrixGrid;

    @FXML
    private GridPane firstMatrixGrid;

    @FXML
    private GridPane secondMatrixGrid;

    public void initialize() {
        setAllInitProperties();
        updateAllProperties();
        System.out.println("\nin parent:" + super.backScene + " " + super.forwardScene);
        System.out.println("in child:" + this.backScene + " " + this.forwardScene);
        if (matrixGrid != null) {
            increaseMatrixRowButton.setOnAction(increaseMatrixRow(matrixGrid));
            increaseMatrixColumnButton.setOnAction(increaseMatrixColumn(matrixGrid));
            decreaseMatrixRowButton.setOnAction(decreaseMatrixRow(matrixGrid));
            decreaseMatrixColumnButton.setOnAction(decreaseMatrixColumn(matrixGrid));
        } if (firstMatrixGrid != null) {
            increaseFirstMatrixRowButton.setOnAction(increaseMatrixRow(firstMatrixGrid));
            increaseFirstMatrixColumnButton.setOnAction(increaseMatrixColumn(firstMatrixGrid));
            decreaseFirstMatrixRowButton.setOnAction(decreaseMatrixRow(firstMatrixGrid));
            decreaseFirstMatrixColumnButton.setOnAction(decreaseMatrixColumn(firstMatrixGrid));
        } if (secondMatrixGrid != null) {
            increaseSecondMatrixRowButton.setOnAction(increaseMatrixRow(secondMatrixGrid));
            increaseSecondMatrixColumnButton.setOnAction(increaseMatrixColumn(secondMatrixGrid));
            decreaseSecondMatrixRowButton.setOnAction(decreaseMatrixRow(secondMatrixGrid));
            decreaseSecondMatrixColumnButton.setOnAction(decreaseMatrixColumn(secondMatrixGrid));
        }
    }

    @Override
    public void setAllInitProperties() {
        super.setAllInitProperties();
        switchButtonsState();
    }

    @Override
    public void updateAllProperties() {
        if (matrixGrid != null) {
            decreaseMatrixRowButton.setDisable(matrixGrid.getRowCount() == 1);
            decreaseMatrixColumnButton.setDisable(matrixGrid.getColumnCount() == 1);
            increaseMatrixRowButton.setDisable(matrixGrid.getRowCount() == 9);
            increaseMatrixColumnButton.setDisable(matrixGrid.getColumnCount() == 9);
        } if (firstMatrixGrid != null) {
            decreaseFirstMatrixRowButton.setDisable(firstMatrixGrid.getRowCount() == 1);
            decreaseFirstMatrixColumnButton.setDisable(firstMatrixGrid.getColumnCount() == 1);
            increaseFirstMatrixRowButton.setDisable(firstMatrixGrid.getRowCount() == 9);
            increaseFirstMatrixColumnButton.setDisable(firstMatrixGrid.getColumnCount() == 9);
        } if (secondMatrixGrid != null) {
            decreaseSecondMatrixRowButton.setDisable(secondMatrixGrid.getRowCount() == 1);
            decreaseSecondMatrixColumnButton.setDisable(secondMatrixGrid.getColumnCount() == 1);
            increaseSecondMatrixRowButton.setDisable(secondMatrixGrid.getRowCount() == 9);
            increaseSecondMatrixColumnButton.setDisable(secondMatrixGrid.getColumnCount() == 9);
        }
    }

    public EventHandler<ActionEvent> increaseMatrixRow(GridPane gridPane) {
        return event -> {
            int rowCount = gridPane.getRowCount();
            int colCount = gridPane.getColumnCount();
            for (int col = 0; col < colCount; col++)
                gridPane.add(new TextField(), col, rowCount);
            updateAllProperties();
        };
    }

    public EventHandler<ActionEvent> increaseMatrixColumn(GridPane gridPane) {
        return event -> {
            int rowCount = gridPane.getRowCount();
            int colCount = gridPane.getColumnCount();
            for (int row = 0; row < rowCount; row++)
                gridPane.add(new TextField(), colCount, row);
            updateAllProperties();
        };
    }

    public EventHandler<ActionEvent> decreaseMatrixRow(GridPane gridPane) {
        return event -> {
            int rowCount = gridPane.getRowCount();
            int colCount = gridPane.getColumnCount();
            gridPane.getChildren().removeIf(node -> getRowIndexAsInteger(node) == rowCount);
            gridPane.getChildren().forEach(node -> {
                final int rowIndex = getRowIndexAsInteger(node);
                if (rowCount < rowIndex) {
                    GridPane.setRowIndex(node, rowIndex - 1);
                }
            });
            gridPane.getRowConstraints().remove(rowCount);
        };
    }

    public EventHandler<ActionEvent> decreaseMatrixColumn(GridPane gridPane) {
        return event -> {
            int rowCount = gridPane.getRowCount();
            int colCount = gridPane.getColumnCount();
            for (int row = 0; row < rowCount; row++)
                gridPane.getChildren().remove(colCount, row);
            updateAllProperties();
        };
    }

    public static int getRowIndexAsInteger(Node node) {
        final var a = GridPane.getRowIndex(node);
        if (a == null) {
            return 0;
        }
        return a;
    }
}
