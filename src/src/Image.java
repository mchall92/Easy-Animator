import java.awt.*;

public class Image {
    private String id;
    private Position position;
    private Color color;
    private Shape shape;
    private Size size;

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
