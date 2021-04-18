package cs5004.animator.model;

/** This class implements Feature interface and represents a size feature. */
public class Size implements Feature {
  private int firstArg;
  private int secondArg;

  /**
   * Initialize two args of the size.
   *
   * @param firstArg
   * @param secondArg
   */
  public Size(int firstArg, int secondArg) {
    this.firstArg = firstArg;
    this.secondArg = secondArg;
  }

  public int getFirstArg() {
    return firstArg;
  }

  public int getSecondArg() {
    return secondArg;
  }

  public String toString() {
    return String.format("%d %d", firstArg, secondArg);
  }
}
