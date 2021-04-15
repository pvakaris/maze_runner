package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * A static class that follows all the processes in the application. It keeps track of pressed keys, editing state, etc.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class Monitor {

    private static MazeManipulator maze;
    private static boolean editingMode;
    private static boolean mazeRunning;
    // A set that holds codes of all the keyboard keys that are being pressed at the moment.
    private static final HashSet<KeyCode> pressedKeys = new HashSet<>();

    /**
     * Used to add a new key code to pressed keys set.
     * @param code A code which to add.
     */
    public static void addPressedKey(KeyCode code) {
        pressedKeys.add(code);
    }

    /**
     * Used to remove a key code from pressed keys set.
     * @param code A code which to remove.
     */
    public static void removePressedKey(KeyCode code) {
        pressedKeys.remove(code);
    }

    /**
     * Used to get to know whether the map is in editing mode at the moment.
     * @return true, if it is in the editing mode, false otherwise.
     */
    public static boolean isEditingMode() {
        return editingMode;
    }

    /**
     * Used to get to know whether the maze is running at the moment.
     * @return true, if it is, false otherwise.
     */
    public static boolean isMazeRunning() {
        return mazeRunning;
    }

    /**
     * Used to get a set of all key codes of keys that are pressed currently.
     * @return A set of KeyCodes.
     */
    public static Set<KeyCode> getPressedKeySet() {
        return pressedKeys;
    }

    /**
     * Used to specify whether a maze is running at the moment or not.
     * @param value true if the maze is running, false otherwise.
     */
    public static void setMazeRunning(boolean value) {
        mazeRunning = value;
    }

    /**
     * Used when the "Run maze" button is clicked. Performs the maze running process.
     */
    public static void runMaze() {
        setMazeRunning(true);
        MapManager.getInstance().makeMapCopy();
        MazeManipulator maze = new MazeManipulator(MapManager.getInstance().getCurrentMap());
        setMazeManipulator(maze);
        maze.run();
        setMazeRunning(false);
        Informer.getInstance().updateMapInfo(MapManager.getInstance().getCurrentMap().toString());
    }

    /**
     * Used to inform the Monitor that a cell was clicked.
     * @param event A MouseEvent (which mouse button was clicked).
     * @param cell A Cell object, which was clicked.
     */
    public static void cellClicked(MouseEvent event, Cell cell) {
        if(editingMode) {
            CellChanger.getInstance().updateCurrentMapReference(MapManager.getInstance().getCurrentMap());
            if(event.getButton() == MouseButton.PRIMARY) {
                cellClickedPrimary(cell);
            }
            else if(event.getButton() == MouseButton.SECONDARY) {
                cellClickedSecondary(cell);
            }
        }
    }

    /**
     * Used to get the maze ManipulatorObject.
     * @return MazeManipulator object if there is one, null otherwise.
     */
    public static MazeManipulator getMazeManipulator() {
        return maze;
    }

    /**
     * Used to set the current MazeManipulator object.
     */
    public static void setMazeManipulator(MazeManipulator mazeManipulator) {
        maze = mazeManipulator;
    }

    /**
     * Used to change the state of the editing mode.
     * @param value true, if the application is in maze editing mode, false otherwise.
     */
    public static void setEditingMode(boolean value) {
        editingMode = value;
    }

    //     ------------        PRIVATE METHODS        ------------

    /**
     * Used to inform the Monitor that a cell was clicked using the left mouse button.
     * @param cell A cell that was clicked.
     */
    private static void cellClickedPrimary(Cell cell) {
        CellChanger.getInstance().putCell(cell, pressedKeys);
    }

    /**
     * Used to inform the Monitor that a cell was clicked using the right mouse button.
     * @param cell A cell that was clicked.
     */
    private static void cellClickedSecondary(Cell cell) {
        CellChanger.getInstance().removeCell(cell, pressedKeys);
    }
}
