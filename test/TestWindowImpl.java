import cs5004.animator.model.*;
import org.junit.Before;
import org.junit.Test;

public class TestWindowImpl {
    private Window window;
    @Before
    public void setUp() {
        window = new WindowImpl(0, 0, 100, 100);
    }

    @Test
    public void TestAddElement() {
        window.addElement("1", 50, 50, 1, 1, 1, Shape.Oval,
                1, 1, 1, 100);
        window.addElement("2", 50, 50, 1, 1, 1, Shape.Rectangle,
                1, 1, 1, 100);
        try{
                window.addElement("1", 50, 50, 1, 1, 1, Shape.Oval,
                1, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("3", -1, 5, 1, 1, 1, Shape.Oval,
                    1, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("4", 50, 50, -1, 1, 1, Shape.Oval,
                    1, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("5", 50, 50, 1, 1, 1, Shape.Oval,
                    -1, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("6", 50, 50, 1, 1, 1, Shape.Oval,
                    1, 1, -1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("7", 110, 50, 1, 1, 1, Shape.Oval,
                    1, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("8", 50, 50, 256, 1, 1, Shape.Oval,
                    1, 1, 1, 100);
        } catch (Exception ignored) {
        }try{
            window.addElement("9", 50, 50, 1, 1, 1, Shape.Oval,
                    60, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("10", 1, 50, 1, 1, 1, Shape.Oval,
                    2, 1, 1, 100);
        } catch (Exception ignored) {
        }
        try{
            window.addElement("11", 99, 50, 1, 1, 1, Shape.Oval,
                    2, 1, 1, 100);
        } catch (Exception ignored) {
        }

    }

}

