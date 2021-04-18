package cs5004.animator.model.buildersrc;

public class TransMove extends Trans {
    private int fromTime;
    private int toTime;
    private int x;
    private int y;

    public int getFromTime() {
        return fromTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getToTime() {
        return toTime;
    }

    public TransMove(String id, int fromTime1, int toTime1, int x, int y) {
        super(id);
        this.fromTime = fromTime1;
        this.toTime = toTime1;
        this.x = x;
        this.y = y;
    }
}
