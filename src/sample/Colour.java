package sample;

public enum Colour {
    WALL("0d141a"),
    START("457b9d"),
    FINISH("e63946"),
    UNKNOWN("e9ecef"),
    DISCOVERED("a3cef1"),
    EXPLORED("6096ba"),
    PATH("ffb703"),
    PATH_START("e85d04"),
    PATH_FINISH("dc2f02");

    // Colour code.
    private final String code;

    /**
     * Colour constructor.
     * @param code The code of the colour.
     */
    Colour(String code) {
        this.code = code;
    }

    /**
     * Used to return the String that the message holds.
     * @return A String of the message
     */
    public String toString() {
        return code;
    }
}
