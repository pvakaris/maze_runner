package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * This class represents a sub-panel of the ToolBox that can be found on the left side of the MazeScene.
 * This VBox holds two buttons: one to reset the map to the state before the maze running algorithm was used and
 * a button to draw the shortest path from the StartCell to the FinishCell of the current map.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class InnerVBoxAfterRun extends VBox {
    private Button resetToBeforeRunButton;
    private Button pathButton;

    /**
     * Public constructor of the InnerVBoxAfterRun object.
     */
    public InnerVBoxAfterRun() {
        setId("inner-toolbox");
        getChildren().addAll(setupPathButton(), setupResetToBeforeRunButton());
    }

    /**
     * Used to enable the "Find path" button.
     */
    public void enablePathButton() {
        pathButton.setDisable(false);
    }

    /**
     * Used to disable the "Find path" button.
     */
    public void disablePathButton() {
        pathButton.setDisable(true);
    }

    //     ------------        PRIVATE METHODS        ------------

    /**
     * Used to setup the "FindPath" button in the VBox that is shown before the run.
     */
    private Button setupPathButton() {
        pathButton = new Button("Find path");
        pathButton.setOnAction(this::showPath);
        return pathButton;
    }

    /**
     * Used to setup the "Reset" button in the VBox that is shown before the run.
     */
    private Button setupResetToBeforeRunButton() {
        resetToBeforeRunButton = new Button("Reset");
        resetToBeforeRunButton.setOnAction(this::resetToBeforeRun);
        return resetToBeforeRunButton;
    }

    /**
     * Method that is called when the "Reset" button of this VBox is clicked.
     * @param event An action event which this method is called after.
     */
    private void resetToBeforeRun(ActionEvent event) {
        ToolBoxManager.getInstance().resetToBeforeRunWasClicked();
    }

    /**
     * Method that is called when the "Show path" button of this VBox is clicked.
     * @param event An action event after which this method is called.
     */
    private void showPath(ActionEvent event) {
        ToolBoxManager.getInstance().showPathWasClicked();
    }
}
