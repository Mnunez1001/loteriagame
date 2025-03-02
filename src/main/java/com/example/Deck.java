package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
     private List<Card> cards;
    
    private static final String[][] PREDEFINED_CARDS = {
        {"The Rooster", "/mage00.jpg"},
        {"The Devil", "/mage1.jpg"},
        {"The Lady", "/mage2.jpg"},
        {"The Gentlemen", "/mage3.jpg"},
        {"The Umbrella", "/mage4.jpg"},
        {"The Mermaid", "/mage5.jpg"},
        {"The Ladder", "/mage6.jpg"},
        {"The Bottle", "/mage7.jpg"},
        {"The Barrel", "/mage8.jpg"},
        {"The Tree", "/mage9.jpg"},
        {"The Melon", "/mage10.jpg"},
        {"The Brave One", "/mage11.jpg"},
        {"The Bonnet", "/mage12.jpg"},
        {"The Death", "/mage13.jpg"},
        {"The Pear", "/mage14.jpg"},
        {"The Flag", "/mage15.jpg"},
        {"The Mandolin", "/mage16.jpg"},
        {"The Cello", "/mage17.jpg"},
        {"The Heron", "/mage18.jpg"},
        {"The Bird", "/mage19.jpg"},
        {"The Hand", "/mage20.jpg"},
        {"The Boot", "/mage21.jpg"},
        {"The Moon", "/mage22.jpg"},
        {"The Parrot", "/mage23.jpg"},
        {"The Drunk", "/mage24.jpg"},
        {"The Black Man", "/mage25.jpg"},
        {"The Heart", "/mage26.jpg"},
        {"The Watermelon", "/mage27.jpg"},
        {"The Drum", "/mage28.jpg"},
        {"The Shrimp", "/mage29.jpg"},
        {"The Arrows", "/mage30.jpg"},
        {"The Musician", "/mage31.jpg"},
        {"The Spider", "/mage32.jpg"},
        {"The Soldier", "/mage33.jpg"},
        {"The Star", "/mage34.jpg"},
        {"The Ladle", "/mage35.jpg"},
        {"The World", "/mage36.jpg"},
        {"The Apache", "/mage37.jpg"},
        {"The Cactus", "/mage38.jpg"},
        {"The Scorpion", "/mage39.jpg"},
        {"The Rose", "/mage40.jpg"},
        {"The Skull", "/mage41.jpg"},
        {"The Bell", "/mage42.jpg"},
        {"The Water Pitcher", "/mage43.jpg"},
        {"The Deer", "/mage44.jpg"},
        {"The Sun", "/mage45.jpg"},
        {"The Crown", "/mage46.jpg"},
        {"The Canoe", "/mage47.jpg"},
        {"The Pine", "/mage48.jpg"},
        {"The Fish", "/mage49.jpg"},
        {"The Palm", "/mage50.jpg"},
        {"The Flowerpot", "/mage51.jpg"},
        {"The Harp", "/mage52.jpg"},
        {"The Frog", "/mage53.jpg"},   
        
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
