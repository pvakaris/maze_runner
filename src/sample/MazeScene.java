package sample;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

/**
 * This class represents a maze scene. This is the main scene of the application where all the fun things happen.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class MazeScene extends Scene {
    // SINGLETON pattern
    private static MazeScene instance;

    private Label appStatus;
    private Label mazeInfo;

    // A tool box with buttons for editing and playing with maze.
    private ToolBox leftToolBox;
    // The main pane of the scene
    private BorderPane mainPane;
    // Map that is represented at the center of the mainPane.
    private Map map;
    // Object used to update mazeInfo and appStatus labels.
    private Informer informer;

    /**
     * Private constructor of the MazeScene.
     * @param root
     */
    private MazeScene(Pane root){
        super(root);
        createMenuBar(root);
        createMainPane(root);
        MapManager.getInstance().setMazeScene(this);
        getStylesheets().add("design.css");
        setWhenToInformMonitor();
    }

    /**
     * Used to get the MazeScene object.
     * @return A MazeSceneObject.
     */
    public static MazeScene getInstance() {
        if(instance == null) {
            VBox root = new VBox();
            instance = new MazeScene(root);
        }
        return instance;
    }

    /**
     * Create the menu bar at the top of the window.
     * @param parent Pane which add the menu bar to.
     */
    private void createMenuBar(Pane parent) {
        Pane holster = new VBox();
        MenuBar menuBar = new MenuBar();
        //
        holster.getChildren().add(menuBar);
        parent.getChildren().add(holster);

        menuBar.getMenus().addAll(setupGeneralMenu(), setupHelpMenu());
    }

    /**
     * Used to setup the general section of the menu.
     * @return A menu object.
     */
    private Menu setupGeneralMenu() {
        Menu generalMenu = new Menu("General");
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(e -> System.exit(0));
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

        generalMenu.getStyleClass().add("menu-label");
        generalMenu.getItems().add(quitItem);
        return generalMenu;
    }

    /**
     * Used to setup the help section of the menu.
     * @return A menu object.
     */
    private Menu setupHelpMenu() {
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(this::aboutMessage);
        aboutItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN));

        MenuItem instructionsItem = new MenuItem("Instructions");
        instructionsItem.setOnAction(this::instructionsMessage);
        instructionsItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.SHORTCUT_DOWN));

        helpMenu.getItems().addAll(aboutItem, instructionsItem);
        helpMenu.getStyleClass().add("menu-label");
        return helpMenu;
    }

    /**
     * Send an information alert storing general information about the application.
     * @param event An action on which this method is called.
     */
    private void aboutMessage(ActionEvent event) {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("A little bit of information about this software");
        aboutAlert.setContentText("Version 1.0\nDeveloper Vakaris Paulavičius\nAll the rights are reserved");
        aboutAlert.showAndWait();
    }

    /**
     * Used to display the instructions of the application.
     * @param event An event on which the alert is displayed.
     */
    private void instructionsMessage(ActionEvent event) {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("Instructions");
        aboutAlert.setHeaderText("How to edit");
        aboutAlert.setContentText("""
                While in editing mode:
                 - Left mouse click: place a wall cell
                 - Right mouse click: remove a wall cell
                 - S down left mouse click: place a start cell
                 - S down right mouse click: remove a start cell
                 - F down left mouse click: place a finish cell
                 - F down right mouse click: remove a finish cell""");
        aboutAlert.showAndWait();
    }


    /**
     * Create the main pane 2which holds the maze and other information.
     * @param parent A pane which put the elements to
     */
    private void createMainPane(Pane parent) {

        // Setup status label.
        appStatus = new Label();

        // Setup information label.
        mazeInfo = new Label();

        // Setup the informer object
        setupInformer();

        AnchorPane upperAnchor = new AnchorPane(mazeInfo);
        mainPane = new BorderPane(null, upperAnchor, null, appStatus, null);

        // Setup a map at the center of the main pane.
        setMap(MapManager.getInstance().getCurrentMap());

        leftToolBox = new ToolBox(map);
        mainPane.setRight(leftToolBox);

        parent.getChildren().add(mainPane);
    }

    /**
     * Used to set a map layout.
     * @param map A map object which to set the map field to.
     */
    public void setMap(Map map) {
        mainPane.setCenter(map);
        informer.updateMapInfo(map.toString());
    }

    /**
     * Used to set on which actions the Monitor should be informed.
     */
    private void setWhenToInformMonitor() {
        setOnKeyPressed(e -> Monitor.addPressedKey(e.getCode()));
        setOnKeyReleased(e -> Monitor.removePressedKey(e.getCode()));
    }

    /**
     * Used to setup the informer object and pass all the necessary references to it.
     */
    private void setupInformer() {
        informer = Informer.getInstance();
        informer.setInformationLabel(mazeInfo);
        informer.setStatusLabel(appStatus);
    }
}
