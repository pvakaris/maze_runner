package sample;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * The object of this class is responsible for carrying-out appropriate actions based on the buttons that were clicked in the ToolBox.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class ToolBoxManager {

    private static ToolBoxManager instance;
    private ToolBox toolBox;
    private InnerVBoxAfterRun afterRunBox;
    private InnerVBoxBeforeRun beforeRunBox;

    /**
     * Private constructor of the ToolBoxManager class.
     */
    private ToolBoxManager() {}

    /**
     * A method used to get the one and only ToolBoxManager object of this application.
     * @return A ToolBoxManager object.
     */
    public static ToolBoxManager getInstance() {
        if(instance == null) {
            instance = new ToolBoxManager();
        }
        return instance;
    }

    // ========          Main ToolBox          ========

    /**
     * Called when the "Run" button in the ToolBox is clicked.
     * @param event A MouseButton which was clicked
     */
    public void runButtonWasClicked(MouseEvent event) {
        if(MapManager.getInstance().getCurrentMap().isValid()) {
            toolBox.setDisable(true);
            Monitor.runMaze(event);
            toolBox.setInnerVBox(afterRunBox);
            toolBox.disableRunButton();
            toolBox.setDisable(false);
        }
    }

    // ========          BeforeRun Inner ToolBox          ========

    /**
     * Called when the "Reset" button in the InnerVBoxBeforeRun is clicked.
     */
    public void resetCompletelyWasClicked() {
        MapManager.getInstance().resetMapCompletely();
    }

    /**
     * Called when the "Edit" button in the InnerVBoxBeforeRun is clicked.
     */
    public void editButtonWasClicked() {
        boolean editing = Monitor.isEditingMode();
        if(!editing) {
            toolBox.disableRunButton();
            editing = true;
            Informer.getInstance().updateStatusLabel(Message.EDITING_STARTED);

        }
        else {
            toolBox.enableRunButton();
            editing = false;
            Informer.getInstance().updateStatusLabel(Message.EDITING_FINISHED);
        }
        Monitor.setEditingMode(editing);
    }

    // ========          AfterRun Inner ToolBox          ========

    /**
     * Called when the "Find Path" button in the InnerVBoxAfterRun is clicked.
     */
    public void showPathWasClicked() {
        afterRunBox.disablePathButton();
        Monitor.getMazeManipulator().drawShortestPath();
    }

    /**
     * Called when the "Reset" button in the InnerVBoxAfterRun is clicked.
     */
    public void resetToBeforeRunWasClicked() {
        toolBox.enableRunButton();
        afterRunBox.enablePathButton();
        toolBox.setInnerVBox(beforeRunBox);
        MapManager.getInstance().resetMapToBeforeRunState();
        Informer.getInstance().updateMapInfo(MapManager.getInstance().getCurrentMap().toString());
        Monitor.setMazeManipulator(null);
    }

    /**
     * Used to disable the path button in the InnerVBoxAfterRun.
     */
    public void disablePathButton() {
        afterRunBox.disablePathButton();
    }

    // INITIALIZATION STEPS | CALLED WHEN THE TOOLBOX MANAGER IS CREATED FOR THE FIRST TIME

    /**
     * Used to let the ToolBoxManager know about the ToolBox.
     * @param box A reference to the ToolBox object.
     */
    public void setToolBox(ToolBox box) {
        toolBox = box;
    }

    /**
     * Used to let the ToolBoxManager know about the inner box used in the ToolBox before the run.
     * @param beforeBox A reference to the InnerVBoxBeforeRun object.
     */
    public void setInnerBoxUsedBeforeRun(InnerVBoxBeforeRun beforeBox) {
        beforeRunBox = beforeBox;
    }

    /**
     * Used to let the ToolBoxManager know about the inner box used in the ToolBox before the run.
     * @param afterBox A reference to the InnerVBoxAfterRun object.
     */
    public void setInnerBoxUsedAfterRun(InnerVBoxAfterRun afterBox) {
        afterRunBox = afterBox;
    }
}
