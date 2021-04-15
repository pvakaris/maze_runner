package sample;

import javafx.scene.paint.Color;

/**
 * An object of this class represents a walkable cell in the maze map.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class WalkableCell extends Cell {

    // A reference to the cell which this cell was discovered from.
    private Cell previousCell;
    // The status of the cell. For more information, refer to the enum CellStatus.
    private CellStatus status;

    /**
     * Public constructor of the WalkableCell.
     * @param xInMap X coordinate of the WalkableCell.
     * @param yInMap Y coordinate of the WalkableCell.
     */
    public WalkableCell(int xInMap, int yInMap) {
        super(Color.web("e9ecef"), xInMap, yInMap);

        setStatus(CellStatus.UNKNOWN);
        previousCell = null;
    }

    /**
     * Public constructor of the WalkableCell.
     * Used for start and finish cell.
     * @param xInMap X coordinate of the WalkableCell.
     * @param yInMap Y coordinate of the WalkableCell.
     * @param color The color of the WalkableCell.
     */
    public WalkableCell(int xInMap, int yInMap, Color color) {
        super(color, xInMap, yInMap);

        status = CellStatus.UNKNOWN;
        previousCell = null;
    }

    /**
     * Used get the reference to the cell which this cell was discovered from.
     * @return The ancestor cell.
     */
    public Cell getPreviousCell (){
        return previousCell;
    }

    /**
     * Used to set the cell which this cell was discovered from.
     * @param newPreviousCell An ancestor cell.
     */
    public void setPreviousCell (Cell newPreviousCell){
        previousCell = newPreviousCell;
    }

    /**
     * Used to change the status of this WalkableCell to EXPLORED.
     */
    public void setExplored() {
        setStatus(CellStatus.EXPLORED);
        setColor(Color.web("6096ba"));
    }

    /**
     * Used to change the status of this WalkableCell to DISCOVERED.
     */
    public void setDiscovered() {
        setStatus(CellStatus.DISCOVERED);
        setColor(Color.web("a3cef1"));
    }

    /**
     * Used to get the status of the cell.
     * @return CellStatus of the cell.
     */
    public CellStatus getStatus() {
        return status;
    }

    /**
     * Used to set the status of the cell
     * @param status A CellStatus which to set to.
     */
    public void setStatus(CellStatus status) {
        this.status = status;
    }
}
