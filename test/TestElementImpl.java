//import static org.junit.Assert.assertEquals;
//
//import cs5004.animator.model.Color;
//import cs5004.animator.model.Element;
//import cs5004.animator.model.ElementImpl;
//import cs5004.animator.model.Image;
//import cs5004.animator.model.LogNode;
//import cs5004.animator.model.Position;
//import cs5004.animator.model.Shape;
//import cs5004.animator.model.Size;
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * This test class tests methods in ElementImpl.
// */
//
//public class TestElementImpl {
//
//  Element oval0;
//  Element oval1;
//  Element oval2;
//  Element oval3;
//  Element oval4;
//
//  Element rect0;
//  Element rect1;
//  Element rect2;
//  Element rect3;
//  Element rect4;
//
//  @Before
//  public void setUp() {
//    Position position0 = new Position(0, 0);
//    Position position1 = new Position(1, 1);
//    Position position2 = new Position(-1, 1);
//    Position position3 = new Position(-1, -1);
//    Position position4 = new Position(1, -1);
//
//    Size size1 = new Size(1, 1);
//    Size size2 = new Size(2, 3);
//
//    Color color1 = new Color(0.5, 0.5, 0.5);
//    Color color2 = new Color(0.3, 0.1, 0.7);
//
//    oval0 = new ElementImpl("oval0", Shape.Oval, position0, color1,
//        size1, 0, 50);
//    oval1 = new ElementImpl("oval1", Shape.Oval, position1, color1,
//        size1, 0, 50);
//    oval2 = new ElementImpl("oval2", Shape.Oval, position2, color1,
//        size1, 0, 100);
//    oval3 = new ElementImpl("oval3", Shape.Oval, position3, color2,
//        size2, 50, 100);
//    oval4 = new ElementImpl("oval4", Shape.Oval, position4, color2,
//        size2, 20, 120);
//
//    rect0 = new ElementImpl("rect0", Shape.Rectangle, position0, color1,
//        size1, 0, 50);
//    rect1 = new ElementImpl("rect1", Shape.Rectangle, position1, color1,
//        size1, 0, 50);
//    rect2 = new ElementImpl("rect2", Shape.Rectangle, position2, color1,
//        size1, 0, 100);
//    rect3 = new ElementImpl("rect3", Shape.Rectangle, position3, color2,
//        size2, 50, 100);
//    rect4 = new ElementImpl("rect4", Shape.Rectangle, position4, color2,
//        size2, 20, 120);
//  }
//
//  // test illegal Color
//  @Test(expected = IllegalArgumentException.class)
//
//  public void TestInvalidColor1() {
//    Color errorColor = new Color(-0.5, 0.5, 0.5);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidColor2() {
//    Color errorColor = new Color(0.5, -0.5, 0.5);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidColor3() {
//    Color errorColor = new Color(-0.5, 0.5, -0.5);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidColor4() {
//    Color errorColor = new Color(1.2, 0.5, 0.5);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidColor5() {
//    Color errorColor = new Color(0.5, 1.5, 0.5);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidColor6() {
//    Color errorColor = new Color(0.5, 0.5, 1.5);
//  }
//
//  // test illegal Size
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidSize1() {
//    Size errorSize = new Size(-1, 2);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidSize2() {
//    Size errorSize = new Size(1, -2);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidSize3() {
//    Size errorSize = new Size(0, 2);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidSize4() {
//    Size errorSize = new Size(0, 1);
//  }
//
//  @Test
//  public void TestConstructor() {
//    String expected;
//    expected = "Name: oval0\n"
//        + "Type: Oval\n"
//        + "Center: (0.0,0.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, oval0.toString());
//
//    expected = "Name: oval1\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,1.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, oval1.toString());
//
//    expected = "Name: oval2\n"
//        + "Type: Oval\n"
//        + "Center: (-1.0,1.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, oval2.toString());
//
//    expected = "Name: oval3\n"
//        + "Type: Oval\n"
//        + "Center: (-1.0,-1.0), X radius: 2.0, Y radius: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 50\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, oval3.toString());
//
//    expected = "Name: oval4\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,-1.0), X radius: 2.0, Y radius: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 20\n"
//        + "Disappears at t= 120\n";
//    assertEquals(expected, oval4.toString());
//
//    expected = "Name: rect0\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, rect0.toString());
//
//    expected = "Name: rect1\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (1.0,1.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, rect1.toString());
//
//    expected = "Name: rect2\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (-1.0,1.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, rect2.toString());
//
//    expected = "Name: rect3\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (-1.0,-1.0), Width: 2.0, Height: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 50\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, rect3.toString());
//
//    expected = "Name: rect4\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (1.0,-1.0), Width: 2.0, Height: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 20\n"
//        + "Disappears at t= 120\n";
//    assertEquals(expected, rect4.toString());
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor1() {
//    Position position0 = new Position(0, 0);
//    Size size1 = new Size(1, 1);
//    Color color1 = new Color(0.5, 0.5, 0.5);
//
//    Element errorOval = new ElementImpl("oval0", Shape.Oval, position0, color1,
//        size1, -1, 50);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor2() {
//    Position position0 = new Position(0, 0);
//    Size size1 = new Size(1, 1);
//    Color color1 = new Color(0.5, 0.5, 0.5);
//
//    Element errorOval = new ElementImpl("oval0", Shape.Oval, position0, color1,
//        size1, 1, -50);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor3() {
//    Position position0 = new Position(0, 0);
//    Size size1 = new Size(1, 1);
//    Color color1 = new Color(0.5, 0.5, 0.5);
//
//    Element errorOval = new ElementImpl("oval0", Shape.Oval, position0, color1,
//        size1, 50, 50);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor4() {
//    Position position0 = new Position(0, 0);
//    Size size1 = new Size(1, 1);
//    Color color1 = new Color(0.5, 0.5, 0.5);
//
//    Element errorOval = new ElementImpl("oval0", Shape.Oval, position0, color1,
//        size1, 100, 50);
//  }
//
//  @Test
//  public void TestMove() {
//    String[] expectedLog;
//    List<LogNode> logs;
//
//    oval0.move(new Position(3, 1), 5, 10);
//    expectedLog = new String[]{"Shape oval0 moves from (0.0,0.0) to (3.0,1.0) from t=5 to t=10"};
//    logs = oval0.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    oval0.move(new Position(0, 0), 10, 50);
//    expectedLog = new String[]{"Shape oval0 moves from (0.0,0.0) to (3.0,1.0) from t=5 to t=10",
//        "Shape oval0 moves from (3.0,1.0) to (0.0,0.0) from t=10 to t=50"};
//    logs = oval0.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    rect3.move(new Position(0, 0), 50, 57);
//    rect3.move(new Position(10, 21), 70, 100);
//    rect3.move(new Position(-5, 3), 57, 70);
//
//    expectedLog = new String[]{"Shape rect3 moves from (-1.0,-1.0) to (0.0,0.0) from t=50 to t=57",
//        "Shape rect3 moves from (0.0,0.0) to (-5.0,3.0) from t=57 to t=70",
//        "Shape rect3 moves from (-5.0,3.0) to (10.0,21.0) from t=70 to t=100"};
//    logs = rect3.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//  }
//
//  // fromTime out of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove1() {
//    Position position0 = new Position(0, 0);
//    oval3.move(position0, 49, 51);
//  }
//
//  // toTime our of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove2() {
//    Position position0 = new Position(0, 0);
//    oval3.move(position0, 50, 101);
//  }
//
//  // fromTime greater than toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove3() {
//    Position position0 = new Position(0, 0);
//    oval3.move(position0, 55, 51);
//  }
//
//  // fromTime equal to toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove4() {
//    Position position0 = new Position(0, 0);
//    oval3.move(position0, 55, 55);
//  }
//
//  @Test
//  public void TestChangeColor() {
//    String[] expectedLog;
//    List<LogNode> logs;
//
//    rect1.changeColor(new Color(0.1, 1.0, 0.0), 5, 10);
//    expectedLog = new String[]{"Shape rect1 changes color from (0.500, 0.500, 0.500) "
//        + "to (0.100, 1.000, 0.000) from t=5 to t=10"};
//    logs = rect1.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    rect1.changeColor(new Color(0.0, 0.9, 0.1), 10, 50);
//    expectedLog = new String[]{"Shape rect1 changes color from (0.500, 0.500, 0.500) "
//        + "to (0.100, 1.000, 0.000) from t=5 to t=10",
//        "Shape rect1 changes color from (0.100, 1.000, 0.000) to (0.000, 0.900, 0.100) "
//            + "from t=10 to t=50"};
//    logs = rect1.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    oval2.changeColor(new Color(0.0, 0.9, 0.1), 30, 57);
//    oval2.changeColor(new Color(0.35, 0.2, 0.7), 2, 28);
//    oval2.changeColor(new Color(0.0, 0.9, 0.111), 57, 100);
//
//    expectedLog = new String[]{"Shape oval2 changes color from (0.500, 0.500, 0.500) "
//        + "to (0.350, 0.200, 0.700) from t=2 to t=28",
//        "Shape oval2 changes color from (0.350, 0.200, 0.700) to (0.000, 0.900, 0.100) "
//            + "from t=30 to t=57",
//        "Shape oval2 changes color from (0.000, 0.900, 0.100) to (0.000, 0.900, 0.111) "
//            + "from t=57 to t=100"};
//    logs = oval2.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//  }
//
//  // fromTime out of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor1() {
//    Color color1 = new Color(0.5, 0.5, 0.5);
//    oval3.changeColor(color1, 49, 51);
//  }
//
//  // toTime our of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor2() {
//    Color color1 = new Color(0.5, 0.5, 0.5);
//    oval3.changeColor(color1, 50, 101);
//  }
//
//  // fromTime greater than toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor3() {
//    Color color1 = new Color(0.5, 0.5, 0.5);
//    oval3.changeColor(color1, 55, 51);
//  }
//
//  // fromTime equal to toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor4() {
//    Color color1 = new Color(0.5, 0.5, 0.5);
//    oval3.changeColor(color1, 55, 55);
//  }
//
//  @Test
//  public void TestChangeSize() {
//    String[] expectedLog;
//    List<LogNode> logs;
//
//    rect0.changeSize(new Size(0.2, 1.0), 5, 10);
//    expectedLog = new String[]{"Shape rect0 scales from Width: 1.0, Height: 1.0 to "
//        + "Width: 0.2, Height: 1.0 from t=5 to t=10"};
//    logs = rect0.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    rect0.changeSize(new Size(0.9, 0.3), 10, 50);
//    expectedLog = new String[]{"Shape rect0 scales from Width: 1.0, Height: 1.0 to "
//        + "Width: 0.2, Height: 1.0 from t=5 to t=10",
//        "Shape rect0 scales from Width: 0.2, Height: 1.0 to Width: 0.9, Height: 0.3 "
//            + "from t=10 to t=50"};
//    logs = rect0.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//
//    oval1.changeSize(new Size(0.1, 1.9), 30, 50);
//    oval1.changeSize(new Size(2.35, 10.2), 2, 28);
//    oval1.changeSize(new Size(2.5, 7.9), 28, 30);
//
//    expectedLog = new String[]{"Shape oval1 scales from X radius: 1.0, Y radius: 1.0 to "
//        + "X radius: 2.4, Y radius: 10.2 from t=2 to t=28",
//        "Shape oval1 scales from X radius: 2.4, Y radius: 10.2 to "
//            + "X radius: 2.5, Y radius: 7.9 from t=28 to t=30",
//        "Shape oval1 scales from X radius: 2.5, Y radius: 7.9 to "
//            + "X radius: 0.1, Y radius: 1.9 from t=30 to t=50"};
//    logs = oval1.generateLog();
//    for (int i = 0; i < expectedLog.length; i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//  }
//
//  @Test
//  public void TestMixedFeatures() {
//    String[] expectedLog;
//    List<LogNode> logs;
//
//    oval4.changeColor(new Color(0.0, 0.9, 0.1), 30, 57);
//    oval4.changeSize(new Size(0.1, 1.9), 30, 50);
//    oval4.move(new Position(0, 0), 50, 57);
//    oval4.changeColor(new Color(0.35, 0.2, 0.7), 20, 28);
//    oval4.move(new Position(-5, 3), 57, 70);
//    oval4.changeSize(new Size(2.35, 10.2), 23, 28);
//    oval4.changeColor(new Color(0.0, 0.9, 0.111), 57, 100);
//    oval4.move(new Position(10, 21), 70, 120);
//    oval4.changeSize(new Size(2.5, 7.9), 28, 30);
//
//    expectedLog = new String[]
//        {"Shape oval4 moves from (1.0,-1.0) to (0.0,0.0) from t=50 to t=57",
//            "Shape oval4 moves from (0.0,0.0) to (-5.0,3.0) from t=57 to t=70",
//            "Shape oval4 moves from (-5.0,3.0) to (10.0,21.0) from t=70 to t=120",
//            "Shape oval4 changes color from (0.300, 0.100, 0.700) to (0.350, 0.200, 0.700) "
//                + "from t=20 to t=28",
//            "Shape oval4 changes color from (0.350, 0.200, 0.700) to (0.000, 0.900, 0.100) "
//                + "from t=30 to t=57",
//            "Shape oval4 changes color from (0.000, 0.900, 0.100) to (0.000, 0.900, 0.111) "
//                + "from t=57 to t=100",
//            "Shape oval4 scales from X radius: 2.0, Y radius: 3.0 to X radius: 2.4, Y radius: "
//                + "10.2 from t=23 to t=28",
//            "Shape oval4 scales from X radius: 2.4, Y radius: 10.2 to X radius: 2.5, Y radius: "
//                + "7.9 from t=28 to t=30",
//            "Shape oval4 scales from X radius: 2.5, Y radius: 7.9 to X radius: 0.1, Y radius: "
//                + "1.9 from t=30 to t=50"};
//
//    logs = oval4.generateLog();
//    for (int i = 0; i < logs.size(); i += 1) {
//      assertEquals(expectedLog[i], logs.get(i).getInfo());
//    }
//  }
//
//  // fromTime out of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeSize1() {
//    Size size2 = new Size(2, 3);
//    oval3.changeSize(size2, 49, 51);
//  }
//
//  // toTime our of range
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeSize2() {
//    Size size2 = new Size(2, 3);
//    oval3.changeSize(size2, 50, 101);
//  }
//
//  // fromTime greater than toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeSize3() {
//    Size size2 = new Size(2, 3);
//    oval3.changeSize(size2, 55, 51);
//  }
//
//  // fromTime equal to toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeSize4() {
//    Size size2 = new Size(2, 3);
//    oval3.changeSize(size2, 55, 55);
//  }
//
//  @Test
//  public void TestGetAppearTime() {
//    assertEquals(0, oval0.getAppearTime());
//    assertEquals(0, oval1.getAppearTime());
//    assertEquals(0, oval2.getAppearTime());
//    assertEquals(50, oval3.getAppearTime());
//    assertEquals(20, oval4.getAppearTime());
//
//    assertEquals(0, rect0.getAppearTime());
//    assertEquals(0, rect1.getAppearTime());
//    assertEquals(0, rect2.getAppearTime());
//    assertEquals(50, rect3.getAppearTime());
//    assertEquals(20, rect4.getAppearTime());
//  }
//
//  @Test
//  public void TestGetDisappearTime() {
//    assertEquals(50, oval0.getDisappearTime());
//    assertEquals(50, oval1.getDisappearTime());
//    assertEquals(100, oval2.getDisappearTime());
//    assertEquals(100, oval3.getDisappearTime());
//    assertEquals(120, oval4.getDisappearTime());
//
//    assertEquals(50, rect0.getDisappearTime());
//    assertEquals(50, rect1.getDisappearTime());
//    assertEquals(100, rect2.getDisappearTime());
//    assertEquals(100, rect3.getDisappearTime());
//    assertEquals(120, rect4.getDisappearTime());
//  }
//
//  @Test
//  public void TestGetAtTic() {
//    String expectedId;
//    String expectedPosition;
//    String expectedColor;
//    String expectedShape;
//    String expectedSize;
//    Image img;
//
//    oval0.changeColor(new Color(0.0, 0.9, 0.1), 3, 10);
//
//    expectedId = "oval0";
//    expectedPosition = "(0.0,0.0)";
//    expectedColor = "(0.500, 0.500, 0.500)";
//    expectedShape = "Oval";
//    expectedSize = "1.0 1.0";
//
//    img = oval0.getAtTic(0);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//
//    expectedId = "oval0";
//    expectedPosition = "(0.0,0.0)";
//    expectedColor = "(0.429, 0.557, 0.443)";
//    expectedShape = "Oval";
//    expectedSize = "1.0 1.0";
//
//    img = oval0.getAtTic(4);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//
//    oval0.move(new Position(3, 1), 5, 10);
//
//    expectedId = "oval0";
//    expectedPosition = "(1.2,0.4)";
//    expectedColor = "(0.214, 0.729, 0.271)";
//    expectedShape = "Oval";
//    expectedSize = "1.0 1.0";
//
//    img = oval0.getAtTic(7);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//
//    oval0.changeSize(new Size(12, 7), 19, 30);
//    expectedId = "oval0";
//    expectedPosition = "(3.0,1.0)";
//    expectedColor = "(0.000, 0.900, 0.100)";
//    expectedShape = "Oval";
//    expectedSize = "7.0 4.3";
//
//    img = oval0.getAtTic(25);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//
//    expectedId = "oval0";
//    expectedPosition = "(3.0,1.0)";
//    expectedColor = "(0.000, 0.900, 0.100)";
//    expectedShape = "Oval";
//    expectedSize = "12.0 7.0";
//
//    img = oval0.getAtTic(35);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//
//    rect1.move(new Position(3, 1), 5, 10);
//    expectedId = "rect1";
//    expectedPosition = "(1.8,1.0)";
//    expectedColor = "(0.500, 0.500, 0.500)";
//    expectedShape = "Rectangle";
//    expectedSize = "1.0 1.0";
//
//    img = rect1.getAtTic(7);
//    assertEquals(expectedId, img.getId());
//    assertEquals(expectedPosition, img.getPosition().toString());
//    assertEquals(expectedColor, img.getColor().toString());
//    assertEquals(expectedShape, img.getShape().toString());
//    assertEquals(expectedSize, img.getSize().toString());
//  }
//
//  // before appearTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetAtTic1() {
//    oval3.getAtTic(0);
//  }
//
//  // after disappearTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetAtTic2() {
//    oval3.getAtTic(1000);
//  }
//
//  @Test
//  public void TestToString() {
//    String expected;
//    expected = "Name: oval0\n"
//        + "Type: Oval\n"
//        + "Center: (0.0,0.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, oval0.toString());
//
//    expected = "Name: oval1\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,1.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, oval1.toString());
//
//    expected = "Name: oval2\n"
//        + "Type: Oval\n"
//        + "Center: (-1.0,1.0), X radius: 1.0, Y radius: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, oval2.toString());
//
//    expected = "Name: oval3\n"
//        + "Type: Oval\n"
//        + "Center: (-1.0,-1.0), X radius: 2.0, Y radius: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 50\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, oval3.toString());
//
//    expected = "Name: oval4\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,-1.0), X radius: 2.0, Y radius: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 20\n"
//        + "Disappears at t= 120\n";
//    assertEquals(expected, oval4.toString());
//
//    expected = "Name: rect0\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (0.0,0.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, rect0.toString());
//
//    expected = "Name: rect1\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (1.0,1.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 50\n";
//    assertEquals(expected, rect1.toString());
//
//    expected = "Name: rect2\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (-1.0,1.0), Width: 1.0, Height: 1.0, Color: (0.500, 0.500, 0.500)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, rect2.toString());
//
//    expected = "Name: rect3\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (-1.0,-1.0), Width: 2.0, Height: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 50\n"
//        + "Disappears at t= 100\n";
//    assertEquals(expected, rect3.toString());
//
//    expected = "Name: rect4\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (1.0,-1.0), Width: 2.0, Height: 3.0, Color: (0.300, 0.100, 0.700)\n"
//        + "Appears at t= 20\n"
//        + "Disappears at t= 120\n";
//    assertEquals(expected, rect4.toString());
//  }
//}
