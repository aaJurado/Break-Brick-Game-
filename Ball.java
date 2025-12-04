package game;

import java.awt.*;

/**
 * Represents the ball in the game. The ball moves within the game window,
 * bouncing off walls, the paddle, and bricks.
 * 
 * Implements the {@link GameObject} interface to allow rendering and updates.
 * 
 * @author Team
 * @version 1.0
 */
public class Ball implements GameObject {
    /** The x-coordinate of the ball. */
    private int x;

    /** The y-coordinate of the ball. */
    int y;

    /** The diameter of the ball. */
    private int diameter;

    /** The horizontal speed of the ball. */
    private int dx = 6;

    /** The vertical speed of the ball. */
    private int dy = -6;

    /**
     * Constructs a ball with the given position and size.
     * 
     * @param x        The initial x-coordinate of the ball.
     * @param y        The initial y-coordinate of the ball.
     * @param diameter The diameter of the ball.
     */
    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    /**
     * Draws the ball on the screen.
     * 
     * @param brush The graphics context used for drawing.
     * @return void This method does not return a value.
     */
    @Override
    public void draw(Graphics brush) {
        brush.setColor(Color.WHITE);
        brush.fillOval(x, y, diameter, diameter);
    }

    /**
     * Updates the ball's position and handles wall collisions.
     * The ball bounces off the left, right, and top walls.
     * 
     * @return void This method does not return a value.
     */
    @Override
    public void update() {
        x += dx;
        y += dy;

        // Bounce off left and right walls
        if (x <= 0 || x + diameter >= 800) {
            dx = -dx;
        }

        // Bounce off the top wall
        if (y <= 0) {
            dy = -dy;
        }
    }

    /**
     * Reverses the ball's vertical direction when it hits the paddle.
     * 
     * @return void This method does not return a value.
     */
    public void bouncePaddle() {
        dy = -dy;
    }

    /**
     * Checks if the ball has collided with the paddle.
     * 
     * @param paddle The paddle to check collision with.
     * @return {@code true} if the ball hits the paddle, otherwise {@code false}.
     */
    public boolean hitsPaddle(Paddle paddle) {
        return x + diameter > paddle.x && x < paddle.x + paddle.width 
                && y + diameter > paddle.y && y < paddle.y + paddle.height;
    }

    /**
     * Checks if the ball has collided with a brick.
     * 
     * @param brick The brick to check collision with.
     * @return {@code true} if the ball hits the brick, otherwise {@code false}.
     */
    public boolean hitsBrick(Brick brick) {
        return x + diameter > brick.getX() && x < brick.getX() + brick.getWidth() 
                && y + diameter > brick.getY() && y < brick.getY() + brick.getHeight();
    }
}