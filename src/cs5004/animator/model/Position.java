package cs5004.animator.model;

/**
 * This class implements Feature interface and represents a position feature.
 */
public class Position implements Feature {
    private int x;
    private int y;

    /**
     * Initialize x coordinate and y coordinate.
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
