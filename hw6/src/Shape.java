/**
 * This enum represents shape of elements.
 * Different shapes have different sizeTitle and posTitle.
 */
public enum Shape {
    Oval("Oval"),
    Rectangle("Rectangle");

    private String s;

    /**
     * Initialize the String representation of the shape.
     * @param s String representation of the shape
     */
    Shape(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }

    public String sizeTitle() {
        String s = "";
        switch (this) {
            case Oval: s = "xRadius yRadius";
            break;
            case Rectangle: s =  "Width Height";
            break;
        }
        return s;
    }

    public String posTitle() {
        String s = "";
        switch (this) {
            case Oval: s = "Center";
            break;
            case Rectangle: s = "Min corner";
            break;
        }
        return s;
    }
}
