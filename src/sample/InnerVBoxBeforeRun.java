package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * This class represents a sub-panel of the ToolBox that can be found on the left side of the MazeScene.
 * This VBox holds two buttons: one to reset the map to its default layout and
 * a button to edit the layout of the map.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class InnerVBoxBeforeRun extends VBox {

    private Button resetCompletelyButton;
    private Button editButton;

    /**
     * Public constructor of the InnerVBoxBeforeRun object.
     */
    public InnerVBoxBeforeRun() {
        setId("inner-toolbox");
        getChildren().addAll(setupEditButton(), setupResetCompletelyButton());
    }

    //     ------------        PRIVATE METHODS        ------------

    /**
     * Used to setup the "Reset" button in the VBox that is shown before the run.
     */
    private Button setupResetCompletelyButton() {
        resetCompletelyButton = new Button("Reset");
        resetCompletelyButton.setOnAction(this::resetCompletely);
        return resetCompletelyButton;
    }

    /**
     * Used to setup the "Edit maze" button.
     */
    private Button setupEditButton() {
        editButton = new Button("Edit maze");
        editButton.setOnAction(this::editingMaze);
        return editButton;
    }

    /**
     * Called when the "Edit maze" button is clicked.
     * @param event An event on which this method is called.
     */
    private void editingMaze(ActionEvent event) {
        ToolBoxManager.getInstance().editButtonWasClicked();
    }

    /**
     * Called when the "Reset" button is clicked.
     * @param event An event on which this method is called.
     */
    private void resetCompletely(ActionEvent event) {
        ToolBoxManager.getInstance().resetCompletelyWasClicked();
    }
}
