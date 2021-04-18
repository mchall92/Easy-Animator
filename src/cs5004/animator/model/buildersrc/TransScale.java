package cs5004.animator.model.buildersrc;

public class TransScale extends Trans {
    private int firstArg;
    private int secondArg;
    private int fromTime;
    private int toTime;

    public int getFirstArg() {
        return firstArg;
    }

    public int getSecondArg() {
        return secondArg;
    }

    public int getFromTime() {
        return fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public TransScale(String name, int firstArg, int secondArg, int fromTime, int toTime) {
        super(name);
        this.firstArg = firstArg;
        this.secondArg = secondArg;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
