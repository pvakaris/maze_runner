package sample;
import javafx.scene.paint.Color;

/**
 * An object of this class represents a start cell in the maze map.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class StartCell extends WalkableCell {

    /**
     * Public constructor of the WalkableCell.
     * @param xInMap X coordinate of the WalkableCell.
     * @param yInMap Y coordinate of the WalkableCell.
     */
    public StartCell (int xInMap, int yInMap) {
        super(xInMap, yInMap, Color.web("457b9d"));
    }
}
