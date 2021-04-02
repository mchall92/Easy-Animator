public class Size implements Action{
    private double firstArg;
    private double secondArg;

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
