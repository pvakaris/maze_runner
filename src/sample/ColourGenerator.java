package sample;

import javafx.scene.paint.Color;

/**
 * This class is responsible for generating Color objects according to the provided enum.
 */
public class ColourGenerator {

    /**
     * Returns a Color object according to the provided Colour enum element.
     * @param colour A Colour enum object.
     * @return An appropriate Color object.
     */
    public static Color getColour(Colour colour) {
        return Color.web(colour.toString());
    }
}
