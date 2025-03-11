package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Card Game application.
 * Initializes the game UI and launches the JavaFX application.
 */
public class Main extends Application {

    private ImageView[] cardViews = new ImageView[4];

    /**
     * Starts the JavaFX application, initializes the game UI, and sets up the stage.
     *
     * @param primaryStage The main window (stage) for the application.
     */
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = CardController.createGameUI(cardViews);


        Scene scene = new Scene(root, 800, 600);


        String css = getClass().getResource("/application/mystyles.css").toExternalForm();
        scene.getStylesheets().add(css);


        primaryStage.setTitle("Let's Play A Game");
        primaryStage.setScene(scene);
        primaryStage.show();


        CardController.generateRandomCards(cardViews);
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
