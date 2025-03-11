package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of Lotería cards. The deck is predefined with 54 unique
 * cards
 * and can be shuffled and drawn from during the game.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see java.util.List, java.util.Collections, java.util.ArrayList,
 *      com.example.Card
 */

public class Deck {

    /**
     * The list of cards in the deck.
     */
    private List<Card> cards;

    /**
     * A predefined list of Lotería cards with their corresponding image paths.
     */
    private static final String[][] PREDEFINED_CARDS = {
            { "The Rooster", "/com/example/mage00.jpg" },
            { "The Devil", "/com/example/mage1.jpg" },
            { "The Lady", "/com/example/mage2.jpg" },
            { "The Gentlemen", "/com/example/mage3.jpg" },
            { "The Umbrella", "/com/example/mage4.jpg" },
            { "The Mermaid", "/com/example/mage5.jpg" },
            { "The Ladder", "/com/example/mage6.jpg" },
            { "The Bottle", "/com/example/mage7.jpg" },
            { "The Barrel", "/com/example/mage8.jpg" },
            { "The Tree", "/com/example/mage9.jpg" },
            { "The Melon", "/com/example/mage10.jpg" },
            { "The Brave One", "/com/example/mage11.jpg" },
            { "The Bonnet", "/com/example/mage12.jpg" },
            { "The Death", "/com/example/mage13.jpg" },
            { "The Pear", "/com/example/mage14.jpg" },
            { "The Flag", "/com/example/mage15.jpg" },
            { "The Mandolin", "/com/example/mage16.jpg" },
            { "The Cello", "/com/example/mage17.jpg" },
            { "The Heron", "/com/example/mage18.jpg" },
            { "The Bird", "/com/example/mage19.jpg" },
            { "The Hand", "/com/example/mage20.jpg" },
            { "The Boot", "/com/example/mage21.jpg" },
            { "The Moon", "/com/example/mage22.jpg" },
            { "The Parrot", "/com/example/mage23.jpg" },
            { "The Drunk", "/com/example/mage24.jpg" },
            { "The Black Man", "/com/example/mage25.jpg" },
            { "The Heart", "/com/example/mage26.jpg" },
            { "The Watermelon", "/com/example/mage27.jpg" },
            { "The Drum", "/com/example/mage28.jpg" },
            { "The Shrimp", "/com/example/mage29.jpg" },
            { "The Arrows", "/com/example/mage30.jpg" },
            { "The Musician", "/com/example/mage31.jpg" },
            { "The Spider", "/com/example/mage32.jpg" },
            { "The Soldier", "/com/example/mage33.jpg" },
            { "The Star", "/com/example/mage34.jpg" },
            { "The Ladle", "/com/example/mage35.jpg" },
            { "The World", "/com/example/mage36.jpg" },
            { "The Apache", "/com/example/mage37.jpg" },
            { "The Cactus", "/com/example/mage38.jpg" },
            { "The Scorpion", "/com/example/mage39.jpg" },
            { "The Rose", "/com/example/mage40.jpg" },
            { "The Skull", "/com/example/mage41.jpg" },
            { "The Bell", "/com/example/mage42.jpg" },
            { "The Water Pitcher", "/com/example/mage43.jpg" },
            { "The Deer", "/com/example/mage44.jpg" },
            { "The Sun", "/com/example/mage45.jpg" },
            { "The Crown", "/com/example/mage46.jpg" },
            { "The Canoe", "/com/example/mage47.jpg" },
            { "The Pine", "/com/example/mage48.jpg" },
            { "The Fish", "/com/example/mage49.jpg" },
            { "The Palm", "/com/example/mage50.jpg" },
            { "The Flowerpot", "/com/example/mage51.jpg" },
            { "The Harp", "/com/example/mage52.jpg" },
            { "The Frog", "/com/example/mage53.jpg" },

    };

    /**
     * Constructs a new Deck, initializing it with predefined Lotería cards and
     * shuffling them.
     */
    public Deck() {
        cards = new ArrayList<>();
        for (String[] cardData : PREDEFINED_CARDS) {
            cards.add(new Card(cardData[0], cardData[1]));
        }
        shuffleDeck();
    }

    /**
     * Shuffles the deck to randomize the order of the cards.
     */
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the top of the deck.
     *
     * @return The drawn card, or {@code null} if the deck is empty.
     */
    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    /**
     * Creates and returns a new shuffled deck.
     *
     * @return A shuffled list of Lotería cards.
     */

    public static List<Card> getShuffledDeck() {
        Deck deck = new Deck();
        return deck.cards;
    }

}
