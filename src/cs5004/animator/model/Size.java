package cs5004.animator.model;

/** This class implements Feature interface and represents a size feature. */
public class Size implements Feature {
  private int firstArg;
  private int secondArg;

  /**
   * Initialize two args of the size.
   *
   * @param firstArg first argument of size
   * @param secondArg second argument of size
   */
  public Size(int firstArg, int secondArg) {
    if (firstArg < 0 || secondArg < 0) {
      throw new IllegalArgumentException("Size should not be negative");
    }
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
