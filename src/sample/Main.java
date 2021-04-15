package sample;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * The Main class of the application used to launch the application and initialize all the necessary components.
 *
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class Main extends Application {

    private Stage stage;
    private Scene currentScene;

    @Override
    public void start(Stage stage) throws Exception{

        this.stage = stage;
        currentScene = MazeScene.getInstance();
        stage.setResizable(false);
        stage.setTitle("Maze runner");
        setCurrentScene(currentScene);
        stage.show();
    }

    /**
     * Used to set the scene of the main window.
     * @param scene A scene which to place on the stage to.
     */
    public void setCurrentScene(Scene scene) {
        stage.setScene(scene);
        currentScene = scene;
    }

    /**
     * A method responsible for launching the application.
     * @param args An array of arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
