package sample;

/**
 * An object of this class is responsible for keeping track of the current Map object and making its copies when necessary.
 * It also updates the Map that is represented in the MazeScene when needed.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class MapManager {

    private static MapManager instance;

    private MazeScene mazeScene;
    private Map mapCopy;
    private Map map;

    /**
     * Private constructor of the MapManager object.
     */
    private MapManager() {
        setMap(new Map(32, 32));
    }

    /**
     * This method return the one and only MapManager object of this application.
     * @return A MapManager object.
     */
    public static MapManager getInstance() {
        if(instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    /**
     * Used to get the reference to the map object.
     * @return Current Map.
     */
    public Map getCurrentMap() {
        return map;
    }

    /**
     * Used to set a map for the CellChanger.
     * CellChanger performs cell changes on this particular map.
     * @param newMap A map which to set to.
     */
    public void setMap(Map newMap) {
        map = newMap;
    }

    /**
     * Used to set the copy of the map.
     */
    public void setMapCopy(Map copy) {
        mapCopy = copy;
    }

    /**
     * Used to reset the current map and it's copy to the initial version of the map.
     */
    public void resetMapCompletely() {
        map.reset();
        setMapCopy(null);
        mazeScene.setMap(map);
        Informer.getInstance().updateStatusLabel(Message.MAP_RESET_DEFAULT);
    }

    /**
     * Used to reset the current map to the state before the maze running algorithm was deployed.
     */
    public void resetMapToBeforeRunState() {
        setMap(mapCopy);
        mazeScene.setMap(map);
        Informer.getInstance().updateStatusLabel(Message.MAP_RESET_BEFORE_RUN);
    }

    /**
     * Used to make a copy of thr map and store its reference in the mapCopy variable.
     */
    public void makeMapCopy() {
        setMapCopy(map.getCopy());
    }

    /**
     * Used to let the MapManager know about the maze scene, so it could update it
     * whenever the current map changes to a new one.
     * @param scene A MazeScene if the application.
     */
    public void setMazeScene(MazeScene scene) {
        mazeScene = scene;
    }
}
