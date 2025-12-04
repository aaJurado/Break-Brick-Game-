package game;

import java.awt.*;
import java.awt.event.*;

/**
 * An abstract game framework that provides a painted canvas within a window.
 * The game updates every tenth of a second, ensuring smooth rendering.
 * 
 * This class should be extended by specific game implementations (e.g., {@link YourGameName}).
 * It handles window creation, event management, and buffered rendering for a flicker-free experience.
 * 
 * @author Team
 * @version 1.0
 */
abstract class Game extends Canvas {
    /** Determines if the game is running. */
    protected boolean on = true;

    /** The width of the game window. */
    protected int width;

    /** The height of the game window. */
    protected int height;

    /** The off-screen image buffer for smooth rendering. */
    protected Image buffer;

    /**
     * Constructs a game window with the given name and dimensions.
     * 
     * @param name     The title of the game window.
     * @param inWidth  The width of the game window.
     * @param inHeight The height of the game window.
     * @return void This constructor does not return a value.
     */
    public Game(String name, int inWidth, int inHeight) {
        width = inWidth;
        height = inHeight;

        // Create a window (Frame) for the game
        Frame frame = new Frame(name);
        frame.add(this);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Closes the game window when the user clicks the close button.
             * 
             * @param e The window event triggered by closing the window.
             * @return void This method does not return a value.
             */
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Create an off-screen buffer for smoother rendering
        buffer = createImage(width, height);
    }

    /**
     * Paints the game elements onto the canvas.
     * This method must be implemented by subclasses to define game-specific rendering.
     * 
     * @param brush The graphics context used for rendering.
     * @return void This method does not return a value.
     */
    abstract public void paint(Graphics brush);

    /**
     * Updates the game screen by first painting to an off-screen buffer
     * and then drawing it onto the screen, reducing flickering.
     * If the game is running, it waits for 10 milliseconds before repainting.
     * 
     * @param brush The graphics context used for rendering.
     * @return void This method does not return a value.
     */
    public void update(Graphics brush) {
        paint(buffer.getGraphics());
        brush.drawImage(buffer, 0, 0, this);
        if (on) {
            sleep(10);
            repaint();
        }
    }

    /**
     * Pauses execution for the specified time.
     * Used to control the frame rate in the game loop.
     * 
     * @param time The duration to sleep in milliseconds.
     * @return void This method does not return a value.
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {
            // Ignoring the exception since it is non-critical
        }
    }
}
