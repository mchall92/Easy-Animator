package cs5004.animator.model;

/**
 * This class implements Feature interface and represents a color feature.
 */
public class Color implements Feature {
    private int r;
    private int g;
    private int b;

    /**
     * Initialize red percentage, green percentage and blue percentage.
     *
     * @param r
     * @param g
     * @param b
     */
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public String toString() {
        return String.format("(%d, %d, %d)", r, g, b);
    }

    //new added
    public String toSvgString() {
        return String.format("rgb(%d,%d,%d)", r, g, b);
    }
}
