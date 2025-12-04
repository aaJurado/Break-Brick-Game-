package game;

/**
 * Represents a polygon defined by a sequence of points, an offset position, 
 * and a rotation angle. The polygon can be repositioned and rotated.
 * 
 * The initial shape is defined relative to its own origin and is adjusted 
 * accordingly during construction.
 * 
 * @author Team
 * @version 1.0
 */
class Polygon {
    /** The array of points defining the shape of the polygon. */
    private Point[] shape;

    /** The position (offset) of the polygon from its origin. */
    public Point position;

    /** The rotation angle of the polygon in degrees (0-360). */
    public double rotation;

    /**
     * Constructs a polygon with a given shape, position, and rotation.
     * 
     * @param inShape    The array of points defining the shape.
     * @param inPosition The position (offset) of the polygon.
     * @param inRotation The initial rotation of the polygon in degrees.
     * @return void This constructor does not return a value.
     */
    public Polygon(Point[] inShape, Point inPosition, double inRotation) {
        shape = inShape;
        position = inPosition;
        rotation = inRotation;

        // Find the shape's top-left boundary (origin)
        Point origin = shape[0].clone();
        for (Point p : shape) {
            if (p.x < origin.x) origin.x = p.x;
            if (p.y < origin.y) origin.y = p.y;
        }

        // Adjust all points relative to the new origin
        for (Point p : shape) {
            p.x -= origin.x;
            p.y -= origin.y;
        }
    }

    /**
     * Returns the actual points of the polygon after applying rotation and position offset.
     * 
     * @return An array of points representing the transformed polygon.
     */
    public Point[] getPoints() {
        Point center = findCenter();
        Point[] points = new Point[shape.length];
        for (int i = 0; i < shape.length; i++) {
            Point p = shape[i];
            double x = ((p.x - center.x) * Math.cos(Math.toRadians(rotation)))
                    - ((p.y - center.y) * Math.sin(Math.toRadians(rotation)))
                    + center.x / 2 + position.x;
            double y = ((p.x - center.x) * Math.sin(Math.toRadians(rotation)))
                    + ((p.y - center.y) * Math.cos(Math.toRadians(rotation)))
                    + center.y / 2 + position.y;
            points[i] = new Point(x, y);
        }
        return points;
    }

    /**
     * Checks if a given point is inside the polygon using the ray-casting algorithm.
     * 
     * @param point The point to check.
     * @return {@code true} if the point is inside the polygon, {@code false} otherwise.
     */
    public boolean contains(Point point) {
        Point[] points = getPoints();
        double crossingNumber = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            if ((((points[i].x < point.x) && (point.x <= points[j].x)) ||
                    ((points[j].x < point.x) && (point.x <= points[i].x))) &&
                    (point.y > points[i].y + (points[j].y - points[i].y) /
                            (points[j].x - points[i].x) * (point.x - points[i].x))) {
                crossingNumber++;
            }
        }
        return crossingNumber % 2 == 1;
    }

    /**
     * Rotates the polygon by a specified number of degrees.
     * 
     * @param degrees The angle by which to rotate the polygon.
     * @return void This method does not return a value.
     */
    public void rotate(int degrees) {
        rotation = (rotation + degrees) % 360;
    }

    /**
     * Computes the area of the polygon using the shoelace formula.
     * 
     * @return The computed area of the polygon.
     */
    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum += shape[i].x * shape[j].y - shape[j].x * shape[i].y;
        }
        return Math.abs(sum / 2);
    }

    /**
     * Finds the geometric center (centroid) of the polygon.
     * 
     * @return The centroid of the polygon.
     */
    private Point findCenter() {
        Point sum = new Point(0, 0);
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum.x += (shape[i].x + shape[j].x)
                    * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
            sum.y += (shape[i].y + shape[j].y)
                    * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
        }
        double area = findArea();
        return new Point(Math.abs(sum.x / (6 * area)), Math.abs(sum.y / (6 * area)));
    }
}
