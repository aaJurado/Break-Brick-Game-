package game;

import java.awt.Graphics;

/**
 * Represents a game object that can be drawn and updated.
 * Any class implementing this interface must define how it is rendered 
 * and how it updates during the game loop.
 * 
 * @author 
 * @version 1.0
 */
public interface GameObject {

    /**
     * Draws the game object on the screen.
     * 
     * @param brush The  used for rendering.
     */
    void draw(Graphics brush);

    /**
     * Updates the game object's state.
     */
    void update();
}