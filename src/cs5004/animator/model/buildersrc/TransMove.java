package cs5004.animator.model.buildersrc;

/** This class records transitions in position attribute. */
public class TransMove extends Trans {
  private int fromTime;
  private int toTime;
  private int x;
  private int y;

  /**
   * Constructor.
   */
  public TransMove(String id, int fromTime1, int toTime1, int x, int y) {
    super(id);
    this.fromTime = fromTime1;
    this.toTime = toTime1;
    this.x = x;
    this.y = y;
  }

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
}
