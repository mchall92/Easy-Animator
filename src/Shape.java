public enum Shape {
    Oval("Oval"),
    Rectangle("Rectangle");

    private String s;

    Shape(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}
