/**
 * This class implements Feature interface and represents a size feature.
 */
public class Size implements Feature {
    private double firstArg;
    private double secondArg;

    /**
     * Initialize two args of the size.
     * @param firstArg
     * @param secondArg
     */
    public Size(double firstArg, double secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    public double getFirstArg() {
        return firstArg;
    }

    public double getSecondArg() {
        return secondArg;
    }

    public String toString() {
        return String.format("%.1f %.1f", firstArg, secondArg);
    }
}
