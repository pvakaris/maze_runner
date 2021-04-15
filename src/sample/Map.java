package sample;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;

/**
 * A map class where the layout of the map is.
 * Created by Vakaris Paulavičius
 * Version 1.5
 */
public class Map extends GridPane {

    private final Cell[][] layout;

    private Cell startCell;
    private Cell finishCell;

    private final int depth;
    private final int width;

    /**
     * Create a new map.
     * @param mapDepth depth of the map.
     * @param mapWidth width of the map.
     */
    public Map(int mapDepth, int mapWidth) {
        layout = new Cell[mapWidth][mapDepth];
        depth = mapDepth;
        width = mapWidth;

        // Makes a default map layout.
        reset();
    }

    /**
     * Return the width of the map.
     * @return map's width.
     */
    public int getVerticalSize() {
        return width;
    }

    /**
     * Return the depth of the map.
     * @return map's depth.
     */
    public int getHorizontalSize() {
        return depth;
    }

    /**
     * Set the start cell in the map's layout.
     * @param startCell A start cell to set.
     */
    public void setStartCell(StartCell startCell) {
        this.startCell = startCell;
    }

    /**
     * Set the finish cell in the map's layout.
     * @param finishCell A finish cell to set.
     */
    public void setFinishCell(FinishCell finishCell) {
        this.finishCell = finishCell;
    }

    /**
     * Return the start cell of the map.
     * @return start cell Cell.
     */
    public Cell getStartCell() {
        return startCell;
    }

    /**
     * Return the finish cell of the map.
     * @return finish cell Cell.
     */
    public Cell getFinishCell() {
        return finishCell;
    }

    /**
     * Return a cell from the map according to the provided coordinates.
     * @param xCoordinate of the cell.
     * @param yCoordinate of the cell.
     * @return a cell Cell.
     */
    public Cell getCell(int xCoordinate, int yCoordinate) {
        return layout[yCoordinate][xCoordinate];
    }

    /**
     * Return the 2D layout of the map.
     * @return 2D array that holds all the map's cells.
     */
    public Cell[][] getLayout() {
        return layout;
    }

    /**
     * Return an ArrayList of 4 neighbouring cells of the given cell. North, East, South, West.
     * @param cell A cell whose neighbours to return.
     * @return An ArrayList with 4 neighbouring cells.
     */
    public ArrayList<WalkableCell> getNeighbourWalkableCells(Cell cell) {

        ArrayList<WalkableCell> neighbours = new ArrayList<>();
        // Check the NORTH cell
        Cell north = getCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap() -1);
        if(cellClassIsRight(north)) {
            neighbours.add( (WalkableCell) north );
        }

        // Check the EAST cell
        Cell east = getCell(cell.getXCoordinateInMap() +1, cell.getYCoordinateInMap());
        if(cellClassIsRight(east)) {
            neighbours.add( (WalkableCell) east );
        }

        // Check the SOUTH cell
        Cell south = getCell(cell.getXCoordinateInMap(), cell.getYCoordinateInMap() +1);
        if(cellClassIsRight(south)) {
            neighbours.add( (WalkableCell) south );
        }

        // Check the WEST cell
        Cell west = getCell(cell.getXCoordinateInMap() -1, cell.getYCoordinateInMap());
        if(cellClassIsRight(west)) {
            neighbours.add( (WalkableCell) west );
        }

        return neighbours;
    }

    /**
     * Used to replace the old cell in the map with a new one.
     * @param oldCell The old cell which to replace to.
     * @param newCell The new cell which to put to the place of the old one to.
     */
    public void replaceCell(Cell oldCell, Cell newCell) {
        getChildren().remove(oldCell);
        lookForStartAndFinishCells(oldCell, newCell);
        setCell(newCell);
        Informer.getInstance().updateMapInfo(toString());
    }

    /**
     * Return if the cell is a border cell.
     * @param cell which to check.
     * @return true if the cell is the border cell, false otherwise.
     */
    public boolean isBorderCell(Cell cell) {
        int x = cell.getXCoordinateInMap();
        int y = cell.getYCoordinateInMap();

        return (y == 0 || y == (width -1) || x == 0 || x == (depth -1));
    }

    /**
     * Create/reset a default layout
     */
    public void reset() {

        // Border consists of walls with all walkable cells inside.
        for(int y = 0; y < width; y ++) {
            for(int x = 0; x < depth; x ++) {
                if(y == 0 || y == (width -1)) {
                    setCell(new WallCell(x, y));
                }
                else {
                    if(x == 0 || x == (depth -1)) {
                        setCell(new WallCell(x, y));
                    }
                    else {
                        setCell(new WalkableCell(x, y));
                    }
                }
            }
            setCell(new WallCell(0, y));
        }

        // Start and finish cells by default are set in the corners.

        setStartCell(new StartCell(1, 1));
        setCell(getStartCell());
        setFinishCell(new FinishCell((depth -2), (width -2)));
        setCell(getFinishCell());
        Informer.getInstance().updateMapInfo(toString());
    }

    /**
     * Used to get the exact copy of this map, but in a new Map object.
     * @return A copy of this map n a new object.
     */
    public Map getCopy() {
        Map mapCopy = new Map(depth, width);
        for(int x = 0; x < width; x ++) {
            for(int y = 0; y < depth; y ++) {
                mapCopy.replaceCell(null, Cells.giveCellClone(layout[x][y]));
            }
        }
        return mapCopy;
    }

    /**
     * Tells i the map can be run. That is, if it has both start and finish cells placed.
     * @return true if the map can be run.
     */
    public boolean isValid() {
        if(finishCell == null) {
            Informer.getInstance().updateStatusLabel(Message.FINISH_CELL_NOT_FOUND);
            return false;
        }
        else if(startCell == null) {
            Informer.getInstance().updateStatusLabel(Message.START_CELL_NOT_FOUND);
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Used to create an initial map by putting the cells without restrictions.
     * @param cell A cell to put to the initial map.
     */
    private void setCell(Cell cell) {
        layout[cell.getYCoordinateInMap()][cell.getXCoordinateInMap()] = cell;
        add(cell, cell.getYCoordinateInMap(), cell.getXCoordinateInMap());
    }

    // ----------------- PRIVATE METHODS

    /**
     * Used to check whether a cell that we want to remove from the map or a cell that we are about to add to the map
     * are either StartCells or FinishCells. This is performed in order to update fields that store references to start
     * and finish cells of the map.
     * @param oldCell An old cell which to consider.
     * @param newCell A new cell which to consider.
     */
    private void lookForStartAndFinishCells(Cell oldCell, Cell newCell) {

        if(newCell instanceof StartCell) setStartCell((StartCell) newCell);
        else if(newCell instanceof FinishCell) setFinishCell((FinishCell) newCell);

        if(oldCell instanceof StartCell) setStartCell(null);
        else if(oldCell instanceof FinishCell) setFinishCell(null);
    }

    /**
     * MazeManipulator information is String format.
     * @return A String with information about the current map state.
     */
    @Override
    public String toString() {
        return (" Map size: " + (width -2) + "x" + (depth -2) +
                " | Start position: " + startPositionToString() + " | Finish position: " + finishPositionToString());
    }

    /**
     * Used to get a String representation of the StartCell coordinates in the map.
     * @return "--:--" if the startCell is null, "xCoordinate:yCoordinate" of the startCell otherwise.
     */
    private String startPositionToString() {
        return startCell == null? "--:--" : startCell.getYCoordinateInMap() + ":" + startCell.getXCoordinateInMap();
    }

    /**
     * Used to get a String representation of the FinishCell coordinates in the map.
     * @return "--:--" if the finishCell is null, "xCoordinate:yCoordinate" of the finishCell otherwise.
     */
    private String finishPositionToString() {
        return finishCell == null? "--:--" : finishCell.getYCoordinateInMap() + ":" + finishCell.getXCoordinateInMap();
    }

    /**
     * Checks whether the specified cell is either a WalkableCell or a StartCell or a FinishCell.
     * @param cell A cell which to check.
     * @return true if the class is right, false otherwise.
     */
    private boolean cellClassIsRight(Cell cell) {
        return cell.getClass().equals(WalkableCell.class) || cell.getClass().equals(StartCell.class) || cell.getClass().equals(FinishCell.class);
    }
}
