package game;

import java.awt.*;
import java.util.ArrayList;

/**
 * The main game class for "Brick Breaker". 
 * Extends {@link Game} to manage game logic, rendering, and user input.
 * The objective is to break all bricks using the ball while preventing it from falling off the screen.
 * 
 * @author Team
 * @version 1.0
 */
public class YourGameName extends Game {

    /** The paddle controlled by the player. */
    private Paddle paddle;

    /** Handles paddle movement using keyboard input. */
    private Paddle.Movement movement;

    /** The ball that moves and interacts with the paddle and bricks. */
    private Ball ball;

    /** The list of bricks that the player must break. */
    private ArrayList<Brick> bricks = new ArrayList<>();

    /** Determines whether the game is currently running. */
    private boolean running = true;

    /** Manages the player's score. */
    private ScoreManager score = new ScoreManager();

    /** Controls the game-over screen rotation effect. */
    private boolean rotateRight = true;

    /**
     * Constructs the game, initializing the paddle, ball, bricks, and key listeners.
     * 
     * @return void This constructor does not return a value.
     */
    public YourGameName() {
        super("Brick Breaker", 800, 600);

        // Initialize paddle and movement
        paddle = new Paddle(350, 550, 100, 10);
        movement = paddle.new Movement();
        addKeyListener(movement);

        // Restart game when 'K' is pressed
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_K) {
                    restart();
                }
            }
        });

        // Initialize ball
        ball = new Ball(400, 400, 15);

        // Generate bricks
        int rows = 5;
        int cols = 10;
        int brickWidth = 75;
        int brickHeight = 20;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                bricks.add(new Brick(col * (brickWidth + 5), row * (brickHeight + 5), brickWidth, brickHeight));
            }
        }
    }

    /**
     * Paints all game elements on the screen, including the paddle, ball, bricks, and UI.
     * It also displays the game-over or win messages with a rotation effect.
     * 
     * @param brush The graphics context used for rendering.
     * @return void This method does not return a value.
     */
    @Override
    public void paint(Graphics brush) {
        Graphics2D g2d = (Graphics2D) brush;

        if (!running) {
            if (bricks.isEmpty()) {
                g2d.setColor(Color.GREEN);
                g2d.drawString("You Win! Score: " + score.getScore(), width / 2 - 60, height / 2 - 20);
            } else {
                g2d.setColor(Color.RED);
                g2d.translate(width / 2, height / 2);
                if (rotateRight) {
                    g2d.rotate(Math.toRadians(15));
                } else {
                    g2d.rotate(Math.toRadians(-15));
                }
                g2d.drawString("Game Over! Score: " + score.getScore(), -50, 0);
                g2d.rotate(rotateRight ? Math.toRadians(-15) : Math.toRadians(15));
                g2d.translate(-width / 2, -height / 2);
                g2d.drawString("Press 'K' to Keep Going", width / 2 - 60, height / 2 + 50);
            }
            return;
        }

        // Background
        brush.setColor(Color.BLACK);
        brush.fillRect(0, 0, width, height);

        // Handle paddle movement
        movement.handleMovement(paddle);
        paddle.draw(brush);
        paddle.update();

        // Draw ball
        ball.draw(brush);
        ball.update();

        // Draw bricks
        for (Brick brick : bricks) {
            brick.draw(brush);
        }

        // Collision detection: Remove bricks when hit
        bricks.removeIf(brick -> {
            if (ball.hitsBrick(brick) && !brick.isHit()) {
                brick.destroy();
                ball.bouncePaddle();
                score.addScore(100);
                return true;
            }
            return false;
        });

        // Paddle collision
        if (ball.hitsPaddle(paddle)) {
            ball.bouncePaddle();
        }

        // Display score
        brush.setColor(Color.WHITE);
        brush.drawString("Score: " + score.getScore(), 10, 200);

        // Winning condition
        if (bricks.isEmpty()) {
            g2d.setColor(Color.GREEN);
            g2d.drawString("You Win! Score: " + score.getScore(), width / 2 - 60, height / 2 - 20);
            running = false;
        }

        // Losing condition
        if (ball.y > height) {
            stop();
            rotateRight = !rotateRight;
        }
    }

    /**
     * Restarts the game, resetting the score, reinitializing the ball and bricks, 
     * and setting the game state to running.
     * 
     * @return void This method does not return a value.
     */
    private void restart() {
        score.resetScore();
        running = true;
        bricks.forEach(brick -> brick.hit = false);
        ball = new Ball(400, 400, 15);
        repaint();
    }

    /**
     * Stops the game when the player loses. Triggers rotation effect.
     * 
     * @return void This method does not return a value.
     */
    public void stop() {
        running = false;
        repaint();
    }

    /**
     * The main method that starts the game.
     * 
     * @param args Command-line arguments (not used).
     * @return void This method does not return a value.
     */
    public static void main(String[] args) {
        YourGameName game = new YourGameName();
        game.repaint();
    }

    /**
     * Manages the player's score, allowing for additions and resets.
     */
    private class ScoreManager {
        /** The player's current score. */
        private int score = 0;

        /**
         * Adds points to the score.
         * 
         * @param points The number of points to add.
         * @return void This method does not return a value.
         */
        public void addScore(int points) {
            score += points;
        }

        /**
         * Retrieves the player's current score.
         * 
         * @return The current score.
         */
        public int getScore() {
            return score;
        }

        /**
         * Resets the player's score to zero.
         * 
         * @return void This method does not return a value.
         */
        public void resetScore() {
            score = 0;
        }
    }
}
