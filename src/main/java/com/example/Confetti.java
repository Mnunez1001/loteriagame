package com.example;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a piece of confetti that falls from the top of the screen.
 * Each piece of confetti has a random color and velocity.
 * 
 * @author Miguel Alexander Nunez Palomares
 * @version 1.0
 * @see javafx.scene.canvas.GraphicsContext, javafx.scene.paint.Color
 */
public class Confetti {
    // Position and velocity of the confetti
    private double x, y, velocityY, velocityX;
    // Color of the confetti
    private final Color color;
    // Array of colors to choose from
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
            Color.PURPLE };
    // Random number generator
    private final Random random = new Random();

     /**
     * Constructs a Confetti object at the specified initial position.
     * The confetti is assigned a random velocity and color.
     * 
     * @param x The initial x-coordinate of the confetti.
     * @param y The initial y-coordinate of the confetti.
     */
    public Confetti(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocityY = 2 + random.nextDouble() * 3;
        this.velocityX = -1.5 + random.nextDouble() * 3;
        this.color = COLORS[random.nextInt(COLORS.length)];
    }

    /**
     * Updates the position of the confetti based on its velocity.
     * If the confetti moves off the bottom of the screen, it resets to the top.
     */
    public void update() {
        x += velocityX;
        y += velocityY;
        if (y > 850) { // Reset position if confetti falls off screen
            y = 0;
            x = random.nextDouble() * 1300;
        }
    }

    /**
     * Draws the confetti on the given graphics context.
     * 
     * @param gc The GraphicsContext used to render the confetti.
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, 8, 8); // Draw confetti as small circles
    }

}
