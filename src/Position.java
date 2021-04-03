/**
 * This class implements Feature interface and represents a position feature.
 */
public class Position implements Feature {
    private double x;
    private double y;

    /**
     * Initialize x coordinate and y coordinate.
     * @param x
     * @param y
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return String.format("(%.1f,%.1f)", x, y);
    }
}
