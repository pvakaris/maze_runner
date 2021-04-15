package sample;

import javafx.scene.paint.Color;

/**
 * An object of this class represents a finish cell in the maze map.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class FinishCell extends WalkableCell {

    /**
     * Public constructor of the FinishCell.
     * @param xInMap X coordinate of the FinishCell.
     * @param yInMap Y coordinate of the FinishCell.
     */
    public FinishCell(int xInMap, int yInMap) {
        super(xInMap, yInMap, ColourGenerator.getColour(Colour.FINISH));
    }
}
