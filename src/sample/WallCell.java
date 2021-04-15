package sample;

import javafx.scene.paint.Color;

/**
 * An object of this class represents a wall cell in the maze map.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class WallCell extends Cell {

    /**
     * Public constructor of the WallCell.
     * @param xInMap X coordinate of the WallCell.
     * @param yInMap Y coordinate of the WallCell.
     */
    public WallCell(int xInMap, int yInMap) {
        super(Color.web("0d141a"), xInMap, yInMap);
    }
}

