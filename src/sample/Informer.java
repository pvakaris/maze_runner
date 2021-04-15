package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Informer object is used to update labels that are represented on the main maze screen.
 * @author Vakaris Paulavičius
 * @version 1.0
 */
public class Informer {

    // SINGLETON pattern
    private static Informer instance;

    private Label statusLabel;
    private Label informationLabel;

    /**
     * Private constructor of the Informer object.
     */
    private Informer() { }

    /**
     * A method that is used to get one and only Informer object.
     * @return An Informer object.
     */
    public static Informer getInstance() {
        if(instance == null) {
            instance = new Informer();
        }
        return instance;
    }

    /**
     * Used to update the status label with a specific message.
     * @param message A message whose String representation to include.
     */
    public void updateStatusLabel(Message message) {
        statusLabel.setText(message.toString());
        showStatusLabel();
    }

    /**
     * Used to update the maze information label.
     * @param text A text which to provide.
     */
    public void updateMapInfo(String text) {
        informationLabel.setText(text);
    }

    /**
     * Used to set a reference to the status label.
     * @param label A label which to consider as a statusLabel.
     */
    public void setStatusLabel(Label label) {
        statusLabel = label;
        // Initially the status label is hidden.
        hideStatusLabel(null);
    }

    /**
     * Used to set a reference to the information label.
     * @param label A label which to consider as an informationLabel.
     */
    public void setInformationLabel(Label label) {
        informationLabel = label;
    }

    /**
     * Used to hide the status label behind the map.
     * @param event AN event on which this method is called.
     */
    private void hideStatusLabel(ActionEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(400),statusLabel);
        tt.setDelay(Duration.millis(3000));
        tt.setToX(+5);
        tt.setToY(-40);
        tt.play();
    }

    /**
     * Used to temporarily show the status label with a new message.
     */
    private void showStatusLabel() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(400), statusLabel);
        tt.setToX(+5);
        tt.setToY(0);
        tt.play();
        tt.setOnFinished(this::hideStatusLabel);
    }
}
