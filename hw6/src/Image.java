/**
 * This class represents an image, which is a slice of the element at a specific time.
 */
public class Image {
    private String id;
    private Position position;
    private Color color;
    private Shape shape;
    private Size size;

    /**
     * Initialize features of this image.
     * @param id
     * @param position
     * @param color
     * @param shape
     * @param size
     */
    public Image(String id, Position position, Color color,
                 Shape shape, Size size) {
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

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public Size getSize() {
        return size;
    }
}
