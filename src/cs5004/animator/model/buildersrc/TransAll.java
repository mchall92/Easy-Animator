package cs5004.animator.model.buildersrc;

import cs5004.animator.model.Shape;

public class TransAll extends Trans {
    private int fromTime;
    private int toTime;
    private int x;
    private int y;
    private int r;
    private int g;
    private int b;
    private int firstArg;
    private int secondArg;

    public int getFromTime() {
        return fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public int getFirstArg() {
        return firstArg;
    }

    public int getSecondArg() {
        return secondArg;
    }


    public TransAll(String name, int fromTime, int toTime, int x, int y, int r, int g, int b, int firstArg, int secondArg) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }
}
