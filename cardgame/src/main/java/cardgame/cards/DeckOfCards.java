package cardgame.cards;

import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
    private final char[] suit = { 'S', 'H', 'D', 'C' };
    private ArrayList<PlayingCard> cards = new ArrayList<>();

    public DeckOfCards() {
        for (char s : suit) {
            for (int i = 1; i < 14; i++) {
                cards.add(new PlayingCard(s, i));

            }
        }
    }


    public ArrayList<PlayingCard> dealHand(int n) {
        Random rng = new Random();
        ArrayList<Integer> chosenCards = new ArrayList<>();
        
        ArrayList<PlayingCard> cardCollection = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int chosenCard = rng.nextInt(0, 52);

            for (Integer ing : chosenCards) {
                while (ing == chosenCard) {
                    chosenCard = rng.nextInt(1, 53);
                }
            }

            cardCollection.add(cards.get(chosenCard));
            chosenCards.add(chosenCard);

        }

        return cardCollection;
    }




}
