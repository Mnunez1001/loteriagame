package com.example;

import javafx.scene.image.Image;

public class Card {
    private String name;
    private String imagePath;
    
    public Card(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
    
    public String getName() {
        return name;
    }
    
    public String getImagePath() {
        return imagePath;
    }

     public Image getImage() {
        return new Image(getClass().getResourceAsStream(imagePath));
    }

}
