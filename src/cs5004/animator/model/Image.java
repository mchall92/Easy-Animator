package cs5004.animator.model;

/** This class represents an image, which is a slice of the element at a specific time. */
public class Image {
  private String id;
  private Position position;
  private ModelColor color;
  private Shape shape;
  private Size size;

  /**
   * Initialize features of this image.
   *
   * @param id id of the element
   * @param position position of the element
   * @param color color of the element
   * @param shape shape of the element
   * @param size size of the element
   */
  public Image(String id, Position position, ModelColor color, Shape shape, Size size) {
    this.id = id;
    this.position = position;
    this.color = color;
    this.shape = shape;
    this.size = size;
  }

  public String getId() {
    return id;
  }

  public Position getPosition() {
    return position;
  }

  public ModelColor getColor() {
    return color;
  }

  public Shape getShape() {
    return shape;
  }

  public Size getSize() {
    return size;
  }
}
