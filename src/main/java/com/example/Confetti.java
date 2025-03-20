package com.example;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Confetti {
    private double x, y, velocityY, velocityX;
    private final Color color;
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
            Color.PURPLE };
    private final Random random = new Random();

    public Confetti(double x, double y) {
        this.x = x;
        this.y = y;
        this.velocityY = 2 + random.nextDouble() * 3;
        this.velocityX = -1.5 + random.nextDouble() * 3;
        this.color = COLORS[random.nextInt(COLORS.length)];
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        if (y > 850) { // Reset position if confetti falls off screen
            y = 0;
            x = random.nextDouble() * 1300;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, 8, 8); // Draw confetti as small circles
    }

}
