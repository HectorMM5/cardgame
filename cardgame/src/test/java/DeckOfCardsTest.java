
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cardgame.cards.DeckOfCards;
import cardgame.cards.PlayingCard;

public class DeckOfCardsTest {

    private DeckOfCards deck;

    @BeforeEach
    public void setUp() {
        deck = new DeckOfCards();
    }

    @Test
    public void testDeckInitialization() {
        assertEquals(52, deck.dealHand(52).size(), "Deck should have 52 unique cards");
    }

    @Test
    public void testDealHandSize() {
        ArrayList<PlayingCard> hand = deck.dealHand(5);
        assertEquals(5, hand.size(), "Dealt hand should have 5 cards");
    }

    @Test
    public void testDealHandNoDuplicates() {
        ArrayList<PlayingCard> hand = deck.dealHand(10);
        Set<PlayingCard> uniqueCards = new HashSet<>(hand);
        assertEquals(hand.size(), uniqueCards.size(), "Dealt hand should not contain duplicates");
    }

    @Test
    public void testCardValuesInRange() {
        ArrayList<PlayingCard> hand = deck.dealHand(20);
        for (PlayingCard card : hand) {
            assertTrue(card.getFace() >= 1 && card.getFace() <= 13,
                "Card face value should be between 1 and 13");
            assertTrue("SHDC".indexOf(card.getSuit()) != -1,
                "Card suit should be one of S, H, D, C");
        }
    }

    @Test
    public void testDealingFullDeck() {
        ArrayList<PlayingCard> fullDeck = deck.dealHand(52);
        assertEquals(52, fullDeck.size(), "Should be able to deal the entire deck");

        Set<PlayingCard> uniqueCards = new HashSet<>(fullDeck);
        assertEquals(52, uniqueCards.size(), "All cards in the full deck deal should be unique");
    }
}