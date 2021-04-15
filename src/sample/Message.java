package sample;

/**
 * An Enum of Messages thta can be shown to the user to inform him about his actions.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public enum Message {

    // All the messages that can be displayed on the screen.

    FINISH_CELL_EXISTS("The finish cell already exists"),
    START_CELL_EXISTS("The start cell already exists"),
    MAP_LOADED("The map was loaded"),
    MAP_LOAD_ERROR("Map has failed to load"),
    SHORTEST_PATH_FOUND("The shortest path was found"),
    SHORTEST_PATH_ERROR("Failed to find the shortest path"),
    MAP_RESET_DEFAULT("Map was reset to default"),
    MAP_RESET_BEFORE_RUN("Map was reset"),
    BORDER_CANNOT_BE_EDITED("Border cell cannot be edited"),
    KEY_COMBINATION_WAS_NOT_RECOGNISED("Key combination was not recognised"),
    EDITING_STARTED("The editing has started"),
    EDITING_FINISHED("The editing has finished"),
    FINISH_REACHED("The finished was reached successfully"),
    FINISH_CANNOT_BE_REACHED("The finish cell cannot be reached"),
    START_CELL_NOT_FOUND("The start cell does not exist"),
    FINISH_CELL_NOT_FOUND("The finish cell does not exist"),
    UNKNOWN("Unknown error");

    // Text of the message.
    private final String text;

    /**
     * Message constructor.
     * @param text A text of the message
     */
    Message(String text) {
        this.text = text;
    }

    /**
     * Used to return the String that the message holds.
     * @return A String of the message
     */
    public String toString() {
        return text;
    }
}

