package sample;

import javafx.scene.input.KeyCode;
import java.util.HashSet;

/**
 * A class that is responsible for modifying the map.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class CellChanger {
    // SINGLETON pattern
    private static CellChanger instance;
    private final Informer informer;
    private Map currentMap;

    /**
     * Private constructor of CellChanger object.
     */
    private CellChanger() {
        informer = Informer.getInstance();
        currentMap = MapManager.getInstance().getCurrentMap();
    }

    /**
     * A static method to get the one and only instance of CellChanger.
     * @return A CellChanger object.
     */
    public static CellChanger getInstance() {
        if(instance == null) {
            instance = new CellChanger();
        }
        return instance;
    }

    /**
     * Used to update the reference so that CellChanger knows about the newest version of the current map.
     * @param map A reference to the current map.
     */
    public void updateCurrentMapReference(Map map) {
        currentMap = map;
    }

    /**
     * Used to put a new cell to the place of the old one.
     * This method considers user pressed keys and performs appropriate change.
     * @param cell An old cell which to replace with a new one to.
     * @param pressedKeys A HashSet of KeyCodes of all keys that are pressed currently.
     */
    public void putCell(Cell cell, HashSet<KeyCode> pressedKeys) {

        if(!currentMap.isBorderCell(cell)) {
            if(startKeyCombination(pressedKeys)) {
                changeToStartCell(cell);
            }
            else if(finishKeyCombination(pressedKeys)) {
                changeToFinishCell(cell);
            }
            else if(wallKeyCombination(pressedKeys)) {
                changeToWallCell(cell);
            }
            else {
                informer.updateStatusLabel(Message.KEY_COMBINATION_WAS_NOT_RECOGNISED);
            }
        }
        else {
            informer.updateStatusLabel(Message.BORDER_CANNOT_BE_EDITED);
        }
    }

    /**
     * Used to replace a cell with a simple walkable cell.
     * This method considers user pressed keys and performs appropriate change.
     * @param cell An old cell which to replace with a new WalkableCell to.
     * @param pressedKeys A HashSet of KeyCodes of all keys that are pressed currently.
     */
    public void removeCell(Cell cell, HashSet<KeyCode> pressedKeys) {
        if(!currentMap.isBorderCell(cell)) {
            if(startKeyCombination(pressedKeys) && cell instanceof StartCell) {
                changeToWalkableCell(cell);
            }
            else if(finishKeyCombination(pressedKeys) && cell instanceof FinishCell) {
                changeToWalkableCell(cell);
            }
            else if(wallKeyCombination(pressedKeys) && cell instanceof WallCell) {
                changeToWalkableCell(cell);
            }
            else {
                informer.updateStatusLabel(Message.KEY_COMBINATION_WAS_NOT_RECOGNISED);
            }
        }
        else {
            informer.updateStatusLabel(Message.BORDER_CANNOT_BE_EDITED);
        }
    }

    //     ------------        PRIVATE METHODS        ------------

    /**
     * Used to change a cell in the map to a new WallCell.
     * @param cell A cell which to change to a WallCell to.
     */
    private void changeToWallCell(Cell cell) {
        if(cell instanceof WalkableCell) {
            currentMap.replaceCell(cell, new WallCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap()));
        }
    }

    /**
     * Used to change a cell in the map to a new WalkableCell.
     * @param cell A cell which to change to a WalkableCell to.
     */
    private void changeToWalkableCell(Cell cell) {
        currentMap.replaceCell(cell, new WalkableCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap()));
    }

    /**
     * Used to change a cell in the map to a new StartCell.
     * @param cell A cell which to change to a StartCell to.
     */
    private void changeToStartCell(Cell cell) {
        if(currentMap.getStartCell() == null) {
            currentMap.replaceCell(cell, new StartCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap()));
        }
        else {
            informer.updateStatusLabel(Message.START_CELL_EXISTS);
        }
    }
    /**
     * Used to change a cell in the map to a new FinishCell.
     * @param cell A cell which to change to a FinishCell to.
     */
    private void changeToFinishCell(Cell cell) {
        if(currentMap.getFinishCell() == null) {
            currentMap.replaceCell(cell, new FinishCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap()));
        }
        else {
            informer.updateStatusLabel(Message.FINISH_CELL_EXISTS);
        }
    }

    /**
     * Used to check whether StartCell editing key combination is pressed.
     * @param pressedKeys A HashSet of KeyCodes of keys that are being pressed.
     * @return true if the StartCell editing key combination is pressed, false otherwise.
     */
    private boolean startKeyCombination(HashSet<KeyCode> pressedKeys) {
            return pressedKeys.contains(KeyCode.S) && !pressedKeys.contains(KeyCode.F);
    }

    /**
     * Used to check whether FinishCell editing key combination is pressed.
     * @param pressedKeys A HashSet of KeyCodes of keys that are being pressed.
     * @return true if the FinishCell editing key combination is pressed, false otherwise.
     */
    private boolean finishKeyCombination(HashSet<KeyCode> pressedKeys) {
        return pressedKeys.contains(KeyCode.F) && !pressedKeys.contains(KeyCode.S);
    }

    /**
     * Used to check whether WallCell editing key combination is pressed.
     * @param pressedKeys A HashSet of KeyCodes of keys that are being pressed.
     * @return true if the WallCell editing key combination is pressed, false otherwise.
     */
    private boolean wallKeyCombination(HashSet<KeyCode> pressedKeys) {
        return !pressedKeys.contains(KeyCode.F) && !pressedKeys.contains(KeyCode.S);
    }
}
