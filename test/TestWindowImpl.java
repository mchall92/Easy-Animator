import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;
import cs5004.animator.model.WindowImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/** This class tests windowImpl. */
public class TestWindowImpl {
  private Window window;
  private Window windowTrue;

  @Before
  public void setUp() {
    window = new WindowImpl(0, 0, 100, 100);
    windowTrue = new WindowImpl(0, 0, 100, 100);
    windowTrue.addElement("1", 50, 50, 1, 1, 1, Shape.Oval, 1, 1, 1, 100);
    windowTrue.addElement("2", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    windowTrue.addElement("a", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    windowTrue.addElement("b", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    windowTrue.addElement("c", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    windowTrue.addElement("deleted", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    windowTrue.move("a", 10, 20, 1, 50);
    windowTrue.changeColor("b", 10, 20, 30, 1, 40);
    windowTrue.scale("c", 10, 2, 1, 30);
  }

  @Test
  public void TestAddElement() {
    window.addElement("q", 50, 50, 1, 1, 1, Shape.Oval, 1, 1, 1, 100);
    window.addElement("w", 50, 50, 1, 1, 1, Shape.Rectangle, 1, 1, 1, 100);
    try {
      window.addElement("q", 50, 50, 1, 1, 1, Shape.Oval, 1, 1, 1, 100);
      fail();
    } catch (Exception ignored) {
    }
    try {
      window.addElement("4", 50, 50, -1, 1, 1, Shape.Oval, 1, 1, 1, 100);
      fail();
    } catch (Exception ignored) {
    }
    try {
      window.addElement("5", 50, 50, 1, 1, 1, Shape.Oval, -1, 1, 1, 100);
      fail();
    } catch (Exception ignored) {
    }
    try {
      window.addElement("6", 50, 50, 1, 1, 1, Shape.Oval, 1, 1, -1, 100);
      fail();
    } catch (Exception ignored) {
    }
    try {
      window.addElement("8", 50, 50, 256, 1, 1, Shape.Oval, 1, 1, 1, 100);
      fail();
    } catch (Exception ignored) {
    }
  }

  @Test
  public void TestRemoveElement() {
    try {
      window.removeElement("1");
      fail();
    } catch (Exception ignored) {
    }
    assertEquals(6, windowTrue.getElementIDAndShape().size());
    windowTrue.removeElement("deleted");
    assertEquals(5, windowTrue.getElementIDAndShape().size());
    try {
      windowTrue.removeElement("3");
      fail();
    } catch (Exception ignored) {
    }
    assertEquals(5, windowTrue.getElementIDAndShape().size());
  }

  @Test
  public void TestMove() {
    try {
      windowTrue.move("1", 1, 1, -1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.move("1", 1, 1, 1, 101);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.move("1", 1, 1, 1, 1);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.move("1", 1, 1, 1, 10);
    try {
      windowTrue.move("1", 2, 2, 5, 15);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.move("1", 10, 10, 10, 11);
    assertEquals(windowTrue.getShapeByTic("1", 1).getPosition().getX(), 50);
    assertEquals(windowTrue.getShapeByTic("1", 10).getPosition().getX(), 1);
    assertEquals(windowTrue.getShapeByTic("1", 11).getPosition().getX(), 10);
  }

  @Test
  public void TestChangeColor() {
    try {
      windowTrue.changeColor("1", -1, 1, 1, 1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.changeColor("1", 1, 1, 1, -1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.changeColor("1", 256, 1, 1, 1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.changeColor("1", 1, 1, 1, 1, 101);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.changeColor("1", 1, 1, 1, 1, 1);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.changeColor("1", 1, 1, 1, 1, 10);
    try {
      windowTrue.changeColor("1", 2, 2, 2, 5, 15);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.changeColor("1", 10, 10, 10, 10, 11);
    assertEquals(windowTrue.getShapeByTic("1", 1).getColor().getR(), 1);
    assertEquals(windowTrue.getShapeByTic("1", 10).getColor().getR(), 1);
    assertEquals(windowTrue.getShapeByTic("1", 11).getColor().getR(), 10);
  }

  @Test
  public void TestChangeSize() {
    try {
      windowTrue.scale("1", -1, 1, 1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.scale("1", 1, 1, -1, 10);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.scale("1", 1, 1, 1, 101);
      fail();
    } catch (Exception ignored) {
    }
    try {
      windowTrue.scale("1", 1, 1, 1, 1);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.scale("1", 1, 1, 1, 10);
    try {
      windowTrue.scale("1", 2, 2, 5, 15);
      fail();
    } catch (Exception ignored) {
    }
    windowTrue.scale("1", 10, 10, 10, 11);
    assertEquals(windowTrue.getShapeByTic("1", 1).getSize().getFirstArg(), 1);
    assertEquals(windowTrue.getShapeByTic("1", 10).getSize().getFirstArg(), 1);
    assertEquals(windowTrue.getShapeByTic("1", 11).getSize().getFirstArg(), 10);
  }

  @Test
  public void TestToString() {
    assertEquals(
        windowTrue.toString(),
        "Shapes:\n"
            + "Type: Oval\n"
            + "Center: (50,50), xRadius: 1, yRadius: 1, Color: (1, 1, 1) Appears" +
                " at t=1 Disappears at t=100\n"
            + "Type: Rectangle\n"
            + "Min Corner: (50,50), Width: 1, Height: 1, Color: (1, 1, 1) Appears " +
                "at t=1 Disappears at t=100\n"
            + "Type: Rectangle\n"
            + "Min Corner: (50,50), Width: 1, Height: 1, Color: (1, 1, 1) Appears " +
                "at t=1 Disappears at t=100\n"
            + "Type: Rectangle\n"
            + "Min Corner: (50,50), Width: 1, Height: 1, Color: (1, 1, 1) Appears" +
                " at t=1 Disappears at t=100\n"
            + "Type: Rectangle\n"
            + "Min Corner: (50,50), Width: 1, Height: 1, Color: (1, 1, 1) Appears " +
                "at t=1 Disappears at t=100\n"
            + "Type: Rectangle\n"
            + "Min Corner: (50,50), Width: 1, Height: 1, Color: (1, 1, 1) Appears " +
                "at t=1 Disappears at t=100\n"
            + "Shape a moves from (50,50) to (10,20) from t=1 to t=50\n"
            + "Shape b changes color from (1, 1, 1) to (10, 20, 30) from t=1 to t=40\n"
            + "Shape c scales from Width: 1, Height: 1 to Width: 10, Height: 2 from " +
                "t=1 to t=30");
  }

  @Test
  public void TestSvgString() {
    assertEquals(
        windowTrue.toSvgString(1),
        "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=" +
                "\"100px\" height=\"100px\" viewBox=\"0 0 100 100\">\n"
            + "<ellipse cx=\"50\" cy=\"50\" rx=\"1\" ry=\"1\" fill=\"rgb(1,1,1)\"" +
                " visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "</ellipse>\n"
            + "<rect x=\"50\" y=\"50\" width=\"1\" height=\"1\" fill=\"rgb(1,1,1)\"" +
                " visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "</rect>\n"
            + "<rect x=\"50\" y=\"50\" width=\"1\" height=\"1\" fill=\"rgb(1,1,1)\" " +
                "visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "<animate attributeName=\"x\" from=\"50\" to=\"10\" begin=\"1.0s\" " +
                "end=\"50.0s\" dur=\"49.0s\" fill=\"freeze\"/>\n"
            + "<animate attributeName=\"y\" from=\"50\" to=\"20\" begin=\"1.0s\" " +
                "end=\"50.0s\" dur=\"49.0s\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect x=\"50\" y=\"50\" width=\"1\" height=\"1\" fill=\"rgb(1,1,1)\" " +
                "visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "<animate attributeName=\"fill\" from=\"rgb(1,1,1)\" to=\"rgb(10,20,30)\"" +
                " begin=\"1.0s\" end=\"40.0s\" dur=\"39.0\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect x=\"50\" y=\"50\" width=\"1\" height=\"1\" fill=\"rgb(1,1,1)\" " +
                "visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "<animate attributeName=\"width\" from=\"1\" to=\"2\" begin=\"1.0s\" " +
                "end=\"30.0s\" dur=\"29.0s\" fill=\"freeze\"/>\n"
            + "<animate attributeName=\"height\" from=\"1\" to=\"2\" begin=\"1.0s\" " +
                "end=\"30.0s\" dur=\"29.0s\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "<rect x=\"50\" y=\"50\" width=\"1\" height=\"1\" fill=\"rgb(1,1,1)\" " +
                "visibility=\"hidden\">\n"
            + "<animate attributeName=\"visibility\" to=\"visible\" begin=\"1s\"/>\n"
            + "<animate attributeName=\"visibility\" to=\"hidden\" begin=\"100s\"/>\n"
            + "</rect>\n"
            + "</svg>");
  }
}
