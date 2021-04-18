package cs5004.animator.model;

/**
 * This enum represents shape of elements. Different shapes have different sizeTitle and posTitle.
 */
public enum Shape {
  Oval("Oval", "xRadius", "yRadius", "Center", "ellipse", "cx", "cy", "rx", "ry", "ELLIPSE"),
  Rectangle(
      "Rectangle",
      "Width",
      "Height",
      "Min Corner",
      "rect",
      "x",
      "y",
      "width",
      "height",
      "RECTANGLE");

  private String type;
  private String sizeTitle1;
  private String sizeTitle2;
  private String posTitle;
  private String svgType;
  private String svgPosTitle1;
  private String svgPosTitle2;
  private String svgSizeTitle1;
  private String svgSizeTitle2;
  private String inputName;

  /**
   * @param type official name of the shape
   * @param sizeTitle1 title of first size argument
   * @param sizeTitle2 title of second size argument
   * @param posTitle title of position
   * @param svgType new added
   * @param svgPosTitle1 new added
   * @param svgPosTitle2 new added
   * @param svgSizeTitle1 new added
   * @param svgSizeTitle2 new added
   */
  Shape(
      String type,
      String sizeTitle1,
      String sizeTitle2,
      String posTitle,
      String svgType,
      String svgPosTitle1,
      String svgPosTitle2,
      String svgSizeTitle1,
      String svgSizeTitle2,
      String inputName) {
    this.type = type;
    this.sizeTitle1 = sizeTitle1;
    this.sizeTitle2 = sizeTitle2;
    this.posTitle = posTitle;
    this.svgType = svgType;
    this.svgPosTitle1 = svgPosTitle1;
    this.svgPosTitle2 = svgPosTitle2;
    this.svgSizeTitle1 = svgSizeTitle1;
    this.svgSizeTitle2 = svgSizeTitle2;
    this.inputName = inputName;
  }

  public String toString() {
    return type;
  }

  public String getType() {
    return type;
  }

  public String getSvgPosTitle1() {
    return svgPosTitle1;
  }

  public String getSvgPosTitle2() {
    return svgPosTitle2;
  }

  public String getSvgSizeTitle1() {
    return svgSizeTitle1;
  }

  public String getSvgSizeTitle2() {
    return svgSizeTitle2;
  }

  public String getSvgType() {
    return svgType;
  }

  public String getSizeTitle1() {
    return sizeTitle1;
  }

  public String getSizeTitle2() {
    return sizeTitle2;
  }

  public String getPosTitle() {
    return posTitle;
  }

  public String getInputName() {
    return inputName;
  }
}
