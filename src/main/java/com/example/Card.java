package com.example;

import javafx.scene.image.Image;

/**
 * Represents a card in the Lotería game. Each card has a name and an associated
 * image.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.scene.image.Image
 */

public class Card {

    /**
     * The name of the card.
     */
    private String name;

    /**
     * The relative path to the image representing the card.
     */
    private String imagePath;

    /**
     * Constructs a Card with a specified name and image path.
     *
     * @param name      The name of the card.
     * @param imagePath The relative path to the image representing the card.
     */
    public Card(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    /**
     * Gets the name of the card.
     *
     * @return The name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the image path of the card.
     *
     * @return The relative path to the card's image.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Loads and returns the image associated with this card.
     *
     * @return An {@code Image} object representing the card's image.
     */
    public Image getImage() {
        return new Image(getClass().getResourceAsStream(imagePath));
    }

}
