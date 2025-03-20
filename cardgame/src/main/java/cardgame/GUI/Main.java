package cardgame.GUI;

import java.util.ArrayList;

import cardgame.cards.DeckOfCards;
import cardgame.cards.PlayingCard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    DeckOfCards deck = new DeckOfCards();
    ArrayList<PlayingCard> hand;


    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();

        Scene scene = new Scene(root, 1000, 750);
        primaryStage.setTitle("Card Viewer");
        primaryStage.setScene(scene);
        
        FlowPane cardArea = new FlowPane(5, 5);
        cardArea.setMaxWidth(Double.MAX_VALUE);

        FlowPane checks = new FlowPane();
        
        ArrayList<StackPane> cardPanes = new ArrayList<>();
        

        Button handBtn = new Button();
        handBtn.setAlignment(Pos.CENTER);
        handBtn.setText("Deal hand");
        handBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                hand = deck.dealHand(5);
                cardPanes.clear();
                cardArea.getChildren().clear();
                
                for (PlayingCard c : hand) {
                    cardPanes.add(createCardView(c));
                }

                cardArea.getChildren().addAll(cardPanes);

            }
        });

        Button checkBtn = new Button();
        checkBtn.setText("Check hand");
        checkBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {

            
                checks.getChildren().clear();

                if (hand.size() != 0) {
                    //Kort sum
                    int sum = 0;
                    for (PlayingCard c : hand) {
                        sum += c.getFace();
                    }

                    Label label = new Label("Hand sum: " + Integer.toString(sum));
                    checks.getChildren().add(label);

                    //Print hjerter 
                    String hjerter = "";
                    for (PlayingCard c : hand) {
                        if (c.getSuit() == 'H') {
                            hjerter += "H" + c.getFace() + " ";
                        }
                    }

                    label = new Label("Du har disse hjerte-kortene:" + hjerter);
                    checks.getChildren().add(label);

                    //Spar dame? 
                    String hasSQ = "False";
                    for (PlayingCard c : hand) {
                        if ((c.getSuit() == 'S') && (c.getFace() == 12)) {
                            hasSQ = "True";
                        }
                    }

                    label = new Label("Har du queen of spades? " + hasSQ);
                    checks.getChildren().add(label);

                    String isFlush = "False";
                    int sameColorAmount = 0;
                    char firstCardSuit = hand.get(0).getSuit();
                    for (PlayingCard c : hand) {
                        if (c.getSuit() == firstCardSuit) {
                            sameColorAmount += 1;

                        } else break;

                        if (sameColorAmount == 5) {
                            isFlush = "True";
                        }
                    }

                    label = new Label("Is flush? " + isFlush);
                    checks.getChildren().add(label);

                    
                }

            }
        });

        checks.setOrientation(Orientation.VERTICAL);
        checks.setAlignment(Pos.CENTER);

        cardArea.setAlignment(Pos.CENTER);
        cardArea.getChildren().addAll(cardPanes);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(handBtn, checkBtn, cardArea, checks);

        primaryStage.setTitle("Card Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

    
    }


    private StackPane createCardView(PlayingCard card) {
        Rectangle border = new Rectangle(80, 120);
        border.setArcWidth(10);
        border.setArcHeight(10);
        border.setFill(Color.WHITE);
        border.setStroke(Color.BLACK);

        Label faceLabel = new Label(Integer.toString(card.getFace()));
        Label suitLabel = new Label(Character.toString(card.getSuit()));

        VBox cardContent = new VBox(5, faceLabel, suitLabel);
        cardContent.setAlignment(Pos.CENTER);

        StackPane cardPane = new StackPane(border, cardContent);
        return cardPane;

    }



    public static void main(String[] args) {
        launch(args);
    }
}