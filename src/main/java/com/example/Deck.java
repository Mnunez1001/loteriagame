package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
     private List<Card> cards;
    
    private static final String[][] PREDEFINED_CARDS = {
        {"El Gallo", "images/gallo.png"},
        {"El Diablito", "images/diablito.png"},
        {"La Dama", "images/dama.png"},
        {"El Catrín", "images/catrin.png"},
        {"El Paraguas", "images/paraguas.png"},
        // Add remaining 49 cards here
    };
    
    public Deck() {
        cards = new ArrayList<>();
        for (String[] cardData : PREDEFINED_CARDS) {
            cards.add(new Card(cardData[0], cardData[1]));
        }
        shuffleDeck();
    }
    
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
    
    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    
    //create a method to get the shuffled deck
    
    public static List<Card> getShuffledDeck() {
        Deck deck = new Deck();
        return deck.cards;
    }

}
