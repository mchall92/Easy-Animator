package cs5004.animator.model.buildersrc;

public class TransChangeColor extends Trans {
    private int fromTime;
    private int toTime;
    private int r;
    private int g;
    private int b;

    public int getFromTime() {
        return fromTime;
    }

    public int getToTime() {
        return toTime;
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

    public TransChangeColor(String id, int fromTime1, int toTime1, int r, int g, int b) {
        super(id);
        this.fromTime = fromTime1;
        this.toTime = toTime1;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
