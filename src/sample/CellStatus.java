package sample;

/**
 * Used to specify the status of a walkable cell.
 *
 * UNKNOWN - The cell hasn't been touched yet.
 * DISCOVERED - The cell is in the queue waiting to be processed.
 * EXPLORED - All the available neighbours of this cell have been put to the queue.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public enum CellStatus {
    UNKNOWN, DISCOVERED, EXPLORED;
}
