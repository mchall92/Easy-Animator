/**
 * This class implements Feature interface and represents a color feature.
 */
public class Color implements Feature {
    private double r;
    private double g;
    private double b;

    /**
     * Initialize red percentage, green percentage and blue percentage.
     * @param r
     * @param g
     * @param b
     */
    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public String toString() {
        return String.format("(%.1f, %.1f, %.1f)", r, g, b);
    }
}
