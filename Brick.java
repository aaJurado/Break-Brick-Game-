package game;

import java.awt.*;

/**
 * Represents a brick in the game. Bricks can be hit by the ball and destroyed.
 * Implements the {@link GameObject} interface to allow rendering and updates.
 * 
 * @author Team
 * @version 1.0
 */
public class Brick implements GameObject {
    /** The x-coordinate of the brick. */
    int x;

    /** The y-coordinate of the brick. */
    int y;

    /** The width of the brick. */
    int width;

    /** The height of the brick. */
    int height;

    /** Indicates whether the brick has been hit. */
    boolean hit = false;

    /**
     * Constructs a brick with the specified position and dimensions.
     * 
     * @param x      The x-coordinate of the brick.
     * @param y      The y-coordinate of the brick.
     * @param width  The width of the brick.
     * @param height The height of the brick.
     * @return void This constructor does not return a value.
     */
    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the brick on the screen if it has not been hit.
     * 
     * @param brush The graphics context used for drawing.
     * @return void This method does not return a value.
     */
    @Override
    public void draw(Graphics brush) {
        if (!hit) {
            brush.setColor(Color.PINK);
            brush.fillRect(x, y, width, height);
            brush.setColor(Color.BLACK);
            brush.drawRect(x, y, width, height);
        }
    }

    /**
     * Marks the brick as destroyed when hit by the ball.
     * 
     * @return void This method does not return a value.
     */
    public void destroy() {
        hit = true;
    }

    /**
     * Checks whether the brick has been hit.
     * 
     * @return {@code true} if the brick has been hit, otherwise {@code false}.
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Gets the x-coordinate of the brick.
     * 
     * @return The x-coordinate of the brick.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the brick.
     * 
     * @return The y-coordinate of the brick.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the brick.
     * 
     * @return The width of the brick.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the brick.
     * 
     * @return The height of the brick.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Updates the brick's state. (Currently not used.)
     * 
     * @return void This method does not return a value.
     */
    @Override
    public void update() {
        // No update logic needed for bricks.
    }
}
