package sample;

/**
 * This class is responsible for cloning cells.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class Cells {

    /**
     * Used to get the exact same cell, just as an another Cell object.
     * @param cell A cell which to clone to.
     * @return A Cell object of the right dynamic sub-class.
     */
    public static Cell giveCellClone(Cell cell) {
        Class cl = cell.getClass();
        int xCoordinate = cell.getXCoordinateInMap();
        int yCoordinate = cell.getYCoordinateInMap();
        if(cl.equals(StartCell.class)) {
            return new StartCell(xCoordinate, yCoordinate);
        }
        else if(cl.equals(FinishCell.class)) {
            return new FinishCell(xCoordinate, yCoordinate);
        }
        else if(cl.equals(WallCell.class)) {
            return new WallCell(xCoordinate, yCoordinate);
        }
        else {
            return new WalkableCell(xCoordinate, yCoordinate);
        }
    }
}
