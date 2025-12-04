package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the paddle in the game. The paddle moves left and right 
 * and prevents the ball from falling off the screen.
 * 
 * Includes an inner class {@link Movement} that handles keyboard input.
 * 
 * @author Team
 * @version 1.0
 */
public class Paddle {
    /** The x-coordinate of the paddle. */
    int x;

    /** The y-coordinate of the paddle. */
    int y;

    /** The width of the paddle. */
    int width;

    /** The height of the paddle. */
    int height;

    /** The speed at which the paddle moves. */
    int speed = 8;

    /**
     * Constructs a paddle with the given position and dimensions.
     * 
     * @param x      The initial x-coordinate of the paddle.
     * @param y      The initial y-coordinate of the paddle.
     * @param width  The width of the paddle.
     * @param height The height of the paddle.
     * @return void This constructor does not return a value.
     */
    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the paddle on the screen.
     * 
     * @param g The graphics context used for drawing.
     * @return void This method does not return a value.
     */
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    /**
     * Updates the paddle's position to ensure it remains within the screen bounds.
     * 
     * @return void This method does not return a value.
     */
    public void update() {
        if (x < 0) {
            x = 0;
        }
        if (x + width > 800) {
            x = 800 - width;
        }
    }

    /**
     * Handles keyboard input for paddle movement.
     * Implements {@link KeyListener} to detect left and right arrow key presses.
     */
    public class Movement implements KeyListener {
        /** Indicates whether the left arrow key is being pressed. */
        private boolean left = false;

        /** Indicates whether the right arrow key is being pressed. */
        private boolean right = false;

        /**
         * Constructs a Movement object with initial key states.
         * 
         * @return void This constructor does not return a value.
         */
        public Movement() {
            left = false;
            right = false;
        }

        /**
         * Handles key press events to start movement.
         * 
         * @param e The key event.
         * @return void This method does not return a value.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = true;
            }
        }

        /**
         * Handles key release events to stop movement.
         * 
         * @param e The key event.
         * @return void This method does not return a value.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                right = false;
            }
        }

        /**
         * Handles typed key events (not used).
         * 
         * @param e The key event.
         * @return void This method does not return a value.
         */
        @Override
        public void keyTyped(KeyEvent e) {
            // Not used.
        }

        /**
         * Moves the paddle based on key input.
         * 
         * @param paddle The paddle object to be moved.
         * @return void This method does not return a value.
         */
        public void handleMovement(Paddle paddle) {
            if (left) {
                paddle.x -= speed;
            }
            if (right) {
                paddle.x += speed;
            }
        }
    }
}
