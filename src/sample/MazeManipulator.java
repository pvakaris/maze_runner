package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * An object of this class is responsible for finding out whether the FinishCell can be reached in the map starting
 * from the StartCell. It also draws the shortest path from those two points.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class MazeManipulator {

    private final Map map;

    /**
     * Public constructor of the MazeManipulator object.
     * @param map A map which this manipulator manipulates.
     */
    public MazeManipulator(Map map) {
        this.map = map;
    }

    /**
     * Used to run the Dijkstra's algorithm on the provided map.
     */
    public void run() {
        // To hold all the discovered walkable cells
        NodeQueue<WalkableCell> row = new NodeQueue<>();
        // First we discover the starting cell
        WalkableCell startCell = (WalkableCell) map.getStartCell();
        dealWithNeighbourCells(row, startCell);
        boolean finishReached = false;

        while(!row.isEmpty() && !finishReached) {
            // Take the first Cell in the queue and find neighbour Cells.
            WalkableCell root = row.dequeue();
            root.setExplored();
            if(!dealWithNeighbourCells(row, root)) {
                finishReached = true;
            }
        }
        printFinishMessage(finishReached);
    }

    /**
     * Used to update the map indicating the shortest path from the StartCell to the FinishCell in other colour.
     */
    public void drawShortestPath() {
        boolean startReached = false;
        WalkableCell finishCell = (WalkableCell) map.getFinishCell();
        finishCell.setColor(Color.web("dc2f02"));
        WalkableCell cell = (WalkableCell) finishCell.getPreviousCell();
        while(!startReached) {
            WalkableCell cellNew = getPreviousCell(cell);
            if(cellNew == map.getStartCell()) {
                cellNew.setColor(Color.web("e85d04"));
                startReached = true;
            }
            cell = cellNew;
        }
    }

    //     ------------        PRIVATE METHODS        ------------

    /**
     * Used to get the WalkableCell which the provided WalkableCell was discovered from
     * and paint this cell using a new colour.
     * @param cell A cell which to modify and whose ancestor cell to look for.
     * @return An ancestor WalkableCell of the provided WalkableCell.
     */
    private WalkableCell getPreviousCell(WalkableCell cell) {
        cell.setColor(Color.web("ffb703"));
        return (WalkableCell) cell.getPreviousCell();
    }

    /**
     * Used to update the status label according to the outcome of the maze run.
     * @param won A value, whether the finish was reached successfully or not.
     */
    private void printFinishMessage(boolean won) {
        if(won) {
            Informer.getInstance().updateStatusLabel(Message.FINISH_REACHED);
        }
        else {
            ToolBoxManager.getInstance().disablePathButton();
            Informer.getInstance().updateStatusLabel(Message.FINISH_CANNOT_BE_REACHED);
        }
    }

    /**
     * Used to discover the neighbour cells of the given cell and check if one of those cells is the FinishCell.
     * @param row A queue where to put all the newly discovered cells to.
     * @param root A cell whose neighbours to check.
     * @return true if the FinishCell was not found among the provided Cell neighbour cells, false otherwise.
     */
    private boolean dealWithNeighbourCells(NodeQueue<WalkableCell> row, Cell root) {
        ArrayList<WalkableCell> neighbours = map.getNeighbourWalkableCells(root);
        for(WalkableCell cell : neighbours) {
            if(cell != null) {
                if(cell == map.getFinishCell()) {
                    cell.setPreviousCell(root);
                    return false;
                }
                else if((CellStatus.UNKNOWN).equals(cell.getStatus())) {  //Check if it is a new undiscovered cell
                    row.enqueue(cell);
                    cell.setDiscovered();
                    cell.setPreviousCell(root);
                }
            }
        }
        return true;
    }
}
