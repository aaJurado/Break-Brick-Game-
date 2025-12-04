package game;

/**
 * Represents a 2D point with x and y coordinates.
 * This class is used throughout the game to store and access positions.
 * Implements {@link Cloneable} to allow duplication of points.
 * 
 * @author Team
 * @version 1.0
 */
public class Point implements Cloneable {
    /** The x-coordinate of the point. */
    public double x;

    /** The y-coordinate of the point. */
    public double y;

    /**
     * Constructs a point with the given x and y coordinates.
     * 
     * @param inX The x-coordinate.
     * @param inY The y-coordinate.
     * @return void This constructor does not return a value.
     */
    public Point(double inX, double inY) {
        x = inX;
        y = inY;
    }

    /**
     * Retrieves the x-coordinate of the point.
     * 
     * @return The x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the point.
     * 
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the point.
     * 
     * @param x The new x-coordinate.
     * @return void This method does not return a value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the point.
     * 
     * @param y The new y-coordinate.
     * @return void This method does not return a value.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Creates and returns a copy of this point.
     * 
     * @return A new {@code Point} object with the same x and y values.
     */
    @Override
    public Point clone() {
        return new Point(x, y);
    }
}
