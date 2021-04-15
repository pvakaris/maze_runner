package sample;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * An object of this class represents a single cell in the maze map.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class Cell extends Rectangle{

    private Color color;
    private final int xCoordinate;
    private final int yCoordinate;

    /**
     * Public constructor of a Cell object.
     * @param color Cell's colour.
     * @param xInMap Cell's x coordinate in map.
     * @param yInMap Cell's y coordinate in map.
     */
    public Cell(Color color, int xInMap, int yInMap) {
        super(15, 15);   // The default size of the cell.

        yCoordinate = xInMap;
        xCoordinate = yInMap;

        this.color = color;
        setFill(this.color);
        this.setOnMousePressed(this::informMonitor);
    }

    /**
     * Used to set the colour of the cell
     * @param newColor A Color object which to set the colour of the Cell to.
     */
    protected void setColor(Color newColor) {
        color = newColor;
        setFill(color);
    }

    /**
     * Used to get the x coordinate of the cell in the map.
     * @return X coordinate of the Cell.
     */
    public int getXCoordinateInMap() {
        return yCoordinate;
    }

    /**
     * Used to get the y coordinate of the cell in the map.
     * @return Y coordinate of the Cell.
     */
    public int getYCoordinateInMap() {
        return xCoordinate;
    }

    /**
     * Used to inform the monitor that this object was clicked.
     * Primarily used while editing the map.
     * @param event An event on which to inform the monitor.
     */
    private void informMonitor(MouseEvent event) {
        Monitor.cellClicked(event, this);
    }

}
