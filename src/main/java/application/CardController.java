package application;

import application.Evaluate;
import application.OpenAPI;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import java.net.URL;
import java.util.*;

/**
 * Handles the game UI, card generation, animations, and hint retrieval.
 */
public class CardController {
    // Mapping card image names to their numerical values
    private static final Map<String, Integer> CARD_VALUES = new HashMap<>() {{
        for (int i = 2; i <= 10; i++) put(i + "_of_hearts.png", i);
        put("Jack_of_hearts.png", 11);
        put("Queen_of_hearts.png", 12);
        put("King_of_hearts.png", 13);
        put("Ace_of_hearts.png", 1);

        for (int i = 2; i <= 10; i++) put(i + "_of_diamonds.png", i);
        put("Jack_of_diamonds.png", 11);
        put("Queen_of_diamonds.png", 12);
        put("King_of_diamonds.png", 13);
        put("Ace_of_diamonds.png", 1);

        for (int i = 2; i <= 10; i++) put(i + "_of_clubs.png", i);
        put("Jack_of_clubs.png", 11);
        put("Queen_of_clubs.png", 12);
        put("King_of_clubs.png", 13);
        put("Ace_of_clubs.png", 1);

        for (int i = 2; i <= 10; i++) put(i + "_of_spades.png", i);
        put("Jack_of_spades.png", 11);
        put("Queen_of_spades.png", 12);
        put("King_of_spades.png", 13);
        put("Ace_of_spades.png", 1);
    }};

    private static String[] selectedCards = new String[4];

    /**
     * Creates the game UI with card display, ads, and controls.
     */
    public static BorderPane createGameUI(ImageView[] cardViews) {
        BorderPane mainContainer = new BorderPane();
        mainContainer.setStyle("-fx-background-color: #1e1e1e;");


        mainContainer.setTop(createAdBanner(" Play Now! A Fun & Fabulous Card Game! ", "#FF69B4"));
        mainContainer.setBottom(createAdBanner(" Upgrade to VIP: No Ads & Exclusive Perks! ", "#FF69B4"));


        mainContainer.setCenter(createGameContent(cardViews));
        return mainContainer;
    }

    /**
     * Creates an advertisement banner.
     */
    private static HBox createAdBanner(String adText, String bgColor) {
        HBox adBanner = new HBox();
        adBanner.setAlignment(Pos.CENTER);
        adBanner.setPadding(new Insets(10));
        adBanner.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 15px;");

        Label adLabel = new Label(adText);
        adLabel.setTextFill(Color.DEEPPINK);
        adLabel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));

        Button adButton = new Button(" Get It ");
        adButton.setStyle("-fx-background-color: #FFDDFA; -fx-text-fill: #D147A3; -fx-font-weight: bold; -fx-border-radius: 20px; -fx-padding: 8px 16px; -fx-font-size: 14px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        adBanner.getChildren().addAll(adLabel, spacer, adButton);
        return adBanner;
    }

    /**
     * Creates the main game content including cards, input, and buttons.
     */
    private static VBox createGameContent(ImageView[] cardViews) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20px; " +
                "-fx-alignment: center; " +
                "-fx-background-color: linear-gradient(to bottom, #FFD1DC, #FAE1DD, #EAC4D5); " +
                "-fx-border-radius: 20px; " +
                "-fx-effect: dropshadow(gaussian, rgba(255,182,193,0.6), 15, 0.6, 0, 0);");

        // Card container
        HBox cardContainer = new HBox(15);
        cardContainer.setStyle("-fx-alignment: center;");

        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitWidth(100);
            cardViews[i].setFitHeight(150);
            cardViews[i].setPreserveRatio(true);

            StackPane cardPane = new StackPane();
            cardPane.setStyle("-fx-background-color: white; -fx-padding: 5px; -fx-border-radius: 5px;");
            cardPane.getChildren().add(cardViews[i]);
            cardContainer.getChildren().add(cardPane);
        }

        // Input field for arithmetic expression
        TextField expressionField = new TextField();
        expressionField.setPromptText("Enter your expression");
        expressionField.setStyle("-fx-font-size: 16px; -fx-pref-width: 300px; -fx-border-radius: 5px;");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        // Buttons
        Button evaluateButton = new Button("Check Answer");
        evaluateButton.setStyle("-fx-background-color: #FADADD; " +
                "-fx-text-fill: #9B5DE5; " +
                "-fx-font-size: 16px; " +
                "-fx-border-radius: 25px; " +
                "-fx-padding: 12px 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-effect: dropshadow(gaussian, rgba(255, 182, 193, 0.6), 12, 0.5, 0, 0); " +
                "-fx-cursor: hand;");
        evaluateButton.setOnAction(e -> {

            String expression = expressionField.getText().trim();
            if (expression.isEmpty()) {
                resultLabel.setText("Oops! Please enter a math expression.");
                resultLabel.setStyle("-fx-text-fill: orange;");
                return;
            }
            System.out.println("Evaluating Expression: " + expression + " = " + Evaluate.evaluate(expression));
            if (isValidExpression(expression)) {
                resultLabel.setText(" Yay! You got it! Next level awaits!");
                resultLabel.setStyle("-fx-text-fill: #4CAF50;");
                expressionField.clear();
                generateRandomCards(cardViews);
            } else {
                resultLabel.setText(" Almost there! One more try!");
                resultLabel.setStyle("-fx-text-fill: red;");
            }
        });

        Button shuffleButton = new Button("Shuffle Magic ");
        shuffleButton.setStyle("-fx-background-color: #FFB6C1; " +
                "-fx-text-fill: #800080; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 20px; " +
                "-fx-padding: 10px 15px; " +
                "-fx-effect: dropshadow(gaussian, pink, 8, 0.6, 0, 0); " +
                "-fx-cursor: hand;");
        shuffleButton.setOnAction(e -> generateRandomCards(cardViews));

        Button hintButton = new Button("Gentle Hint ");
        hintButton.setStyle("-fx-background-color: #E6B0AA; " +
                "-fx-text-fill: #6A0572; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-border-radius: 20px; " +
                "-fx-padding: 10px 15px; " +
                "-fx-effect: dropshadow(gaussian, #FF69B4, 10, 0.5, 0, 0); " +
                "-fx-cursor: hand;");
        hintButton.setOnAction(e -> {
            resultLabel.setText(getSolutionFromGemini());
            resultLabel.setStyle("-fx-text-fill: #FFC0CB;");
        });


        HBox buttonContainer = new HBox(20, evaluateButton, shuffleButton, hintButton);
        buttonContainer.setStyle("-fx-alignment: center;");

        root.getChildren().addAll(cardContainer, expressionField, resultLabel, buttonContainer);
        return root;
    }

     /**
     * Generates random cards with fade-in and fade-out animations.
     */
    public static void generateRandomCards(ImageView[] cardViews) {
        List<String> cardKeys = new ArrayList<>(CARD_VALUES.keySet());
        Collections.shuffle(cardKeys);

        for (ImageView cardView : cardViews) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), cardView);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(event -> updateCardImages(cardViews, cardKeys));
            fadeOut.play();
        }
    }

    private static void updateCardImages(ImageView[] cardViews, List<String> cardKeys) {
        for (int i = 0; i < 4; i++) {
            selectedCards[i] = cardKeys.get(i);
            String imagePath = "/application/cards/" + selectedCards[i];

            URL imageUrl = CardController.class.getResource(imagePath);
            if (imageUrl != null) {
                cardViews[i].setImage(new Image(imageUrl.toExternalForm(), 100, 150, true, true));
            }
        }

        for (ImageView cardView : cardViews) {
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), cardView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        }
    }

    private static boolean isValidExpression(String expression) {
        return Evaluate.evaluate(expression) == 24;
    }

    /**
     * Calls the Gemini AI API to get a hint.
     */
    /**
     * Calls the Gemini AI API to get a hint with a soft and subtle guidance.
     */

    private static String getSolutionFromGemini() {
        String requestBody = "{ \"contents\": [{ \"parts\": [{ \"text\": " +
                "\"Provide a hint for solving 24 using numbers " + Arrays.toString(selectedCards) +
                ". The hint should be subtle and should not reveal the complete solution.\"}]}]}";

        return OpenAPI.callGemini(requestBody);

    }
}