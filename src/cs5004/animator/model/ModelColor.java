package cs5004.animator.model;

/** This class implements Feature interface and represents a color feature. */
public class ModelColor implements Feature {
  private int r;
  private int g;
  private int b;

  /**
   * Initialize red percentage, green percentage and blue percentage.
   *
   * @param r red value
   * @param g green value
   * @param b blue value
   */
  public ModelColor(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Color should be between 0 and 255");
    }
    this.r = r;
    this.g = g;
    this.b = b;
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

  public String toString() {
    return String.format("(%d, %d, %d)", r, g, b);
  }

  // new added
  public String toSvgString() {
    return String.format("rgb(%d,%d,%d)", r, g, b);
  }
}
