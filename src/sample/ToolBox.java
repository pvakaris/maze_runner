package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * This class is an extension of a VBox that is used to hold all the tools necessary to edit and run the maze.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class ToolBox extends VBox {

    private Button runButton;

    private VBox beforeRun;
    private VBox afterRun;

    /**
     * Public constructor of the ToolBox
     * @param map
     */
    public ToolBox(Map map) {
        beforeRun = new InnerVBoxBeforeRun();
        afterRun = new InnerVBoxAfterRun();
        setupToolBoxManager();

        getStylesheets().add("toolbox-stylesheet.css");
        setId("toolbox");
        getChildren().addAll(setupRunButton(), beforeRun);
    }

    /**
     * Used to disable the "Run maze" button.
     */
    public void disableRunButton() {
        runButton.setDisable(true);
    }

    /**
     * Used to enable the "Run maze" button.
     */
    public void enableRunButton() {
        runButton.setDisable(false);
    }

    /**
     * Used to set the inner VBox of the ToolBox.
     * @param box A new VBox to set.
     */
    public void setInnerVBox(VBox box) {
        getChildren().remove(1);
        getChildren().add(box);
    }

    //     ------------        PRIVATE METHODS        ------------


    /**
     * Used to setup the "Run maze" button.
     */
    private Button setupRunButton() {
        runButton = new Button("Run maze");
        runButton.setOnMouseClicked(this::runningMaze);
        return runButton;
    }

    /**
     * Used when the "Run" button is clicked.
     * @param event A "Run" button click.
     */
    private void runningMaze(MouseEvent event) {
        ToolBoxManager.getInstance().runButtonWasClicked(event);
    }

    /**
     * Used to setup the ToolBoxManager object and pass all the necessary references to it.
     */
    private void setupToolBoxManager() {
        ToolBoxManager toolBoxManager  = ToolBoxManager.getInstance();
        toolBoxManager.setToolBox(this);
        toolBoxManager.setInnerBoxUsedAfterRun( (InnerVBoxAfterRun) afterRun);
        toolBoxManager.setInnerBoxUsedBeforeRun( (InnerVBoxBeforeRun) beforeRun);
    }
}
