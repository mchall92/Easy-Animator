//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//import cs5004.animator.model.Image;
//import cs5004.animator.model.LogNode;
//import cs5004.animator.model.Shape;
//import cs5004.animator.model.Window;
//import cs5004.animator.model.WindowImpl;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * This test class tests methods in WindowImpl.
// */
//
//public class TestWindowImpl {
//
//  Window single;
//  Window multiple1;
//  Window multiple2;
//
//  @Before
//  public void setUp() {
//    single = new WindowImpl(100, 100, 200, 200);
//    multiple1 = new WindowImpl(100, 100,700, 200);
//    multiple2 = new WindowImpl(100, 100,200, 700);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor1() {
//    Window error = new WindowImpl(100, 100,0, 200);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor2() {
//    Window error = new WindowImpl(100, 100,20, 0);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor3() {
//    Window error = new WindowImpl(100, 100,-5, 200);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidConstructor4() {
//    Window error = new WindowImpl(100, 100,10, -200);
//  }
//
//  @Test
//  public void TestAddElement() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    expectedId = new String[]{"1"};
//    expectedPosition = new String[]{"(1.0,2.0)"};
//    expectedColor = new String[]{"(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Oval"};
//    expectedSize = new String[]{"3.0 4.0"};
//    i = 0;
//
//    singleImg = single.getAllShapeByTic(29);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    expectedId = new String[]{"rect1", "rect2", "oval1"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)", "(1.0,2.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)",
//        "(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle", "Oval"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0", "3.0 4.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(50);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(79);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//  }
//
//  // position outside of board
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement1() {
//    single.addElement("1", 10000, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement2() {
//    single.addElement("1", 10, 2000, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//  }
//
//  // invalid color
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement3() {
//    single.addElement("1", 10, 100, 1.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement4() {
//    single.addElement("1", 10, 100, 0.5, 1.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement5() {
//    single.addElement("1", 10, 100, 0.5, 0.1, -0.2,
//        Shape.Oval, 3, 4, 0, 100);
//  }
//
//  // invalid size/scale
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement6() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, 0, 4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement7() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 0, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement8() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, -3, 4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement9() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, -4, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement10() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4000, 0, 100);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidAddElement11() {
//    single.addElement("1", 10, 100, 0.5, 0.1, 0.2,
//        Shape.Oval, 1000, 40, 0, 100);
//  }
//
//  @Test
//  public void TestRemoveElement() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    i = 0;
//    single.removeElement("1");
//    singleImg = single.getAllShapeByTic(29);
//    for (Image img : singleImg) {
//      assertNull(img);
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.removeElement("oval1");
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(50);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.removeElement("rect1");
//
//    expectedId = new String[]{"rect2"};
//    expectedPosition = new String[]{"(1.0,21.0)"};
//    expectedColor = new String[]{"(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle"};
//    expectedSize = new String[]{"2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(79);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    i = 0;
//    multiple1.removeElement("rect2");
//    singleImg = multiple1.getAllShapeByTic(29);
//    for (Image img : singleImg) {
//      assertNull(img);
//      i += 1;
//    }
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidRemoveElement1() {
//    multiple2.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple2.removeElement("1");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidRemoveElement2() {
//    multiple2.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple2.removeElement(null);
//  }
//
//  @Test
//  public void TestMove() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//    single.move("1", 2, 3, 10, 50);
//
//    expectedId = new String[]{"1"};
//    expectedPosition = new String[]{"(1.8,2.8)"};
//    expectedColor = new String[]{"(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Oval"};
//    expectedSize = new String[]{"3.0 4.0"};
//    i = 0;
//
//    singleImg = single.getAllShapeByTic(40);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.move("oval1", 100, 100, 20, 30);
//    multiple1.move("rect1", 10, 20, 70, 80);
//    multiple1.move("rect2", 11, 21, 70, 80);
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(5.5,11.0)", "(6.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(75);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.move("rect2", 0, 21, 5, 50);
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(9.1,18.2)", "(9.9,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(79);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//  }
//
//  // invalid ID
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove1() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move(null, 100, 100, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove2() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move("rect", 100, 100, 20, 30);
//  }
//
//  // invalid fromTime or toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove3() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move("rect2", 100, 100, -1, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove4() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move("rect2", 100, 100, 20, 230);
//  }
//
//  // invalid position
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove5() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move("rect2", 100, 1000, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidMove6() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.move("rect2", -100, 100, 20, 30);
//  }
//
//  @Test
//  public void TestChangeColor() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//    single.changeColor("1", 0.2, 0.3, 1.0, 30, 50);
//
//    expectedId = new String[]{"1"};
//    expectedPosition = new String[]{"(1.0,2.0)"};
//    expectedColor = new String[]{"(0.350, 0.200, 0.600)"};
//    expectedShape = new String[]{"Oval"};
//    expectedSize = new String[]{"3.0 4.0"};
//    i = 0;
//
//    singleImg = single.getAllShapeByTic(40);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.changeColor("oval1", 0.99, 1.0, 0.2, 30, 50);
//    multiple1.changeColor("rect1", 1, 0.12, 0.7, 80, 90);
//    multiple1.changeColor("rect2", 0.1, 0.21, 0.7, 5, 80);
//
//    expectedId = new String[]{"rect1", "rect2", "oval1"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)", "(1.0,2.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.108, 0.170, 0.788)",
//        "(0.990, 1.000, 0.200)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle", "Oval"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0", "3.0 4.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(50);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//  }
//
//  // invalid ID
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor1() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor(null, 1.0, 1.0, 1.0, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor2() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect", 1.0, 1.0, 1.0, 20, 30);
//  }
//
//  // invalid fromTime or toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor3() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect2", 1.0, 1.0, 1.0, 2, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor4() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect2", 1.0, 1.0, 1.0, 20, 230);
//  }
//
//  // invalid color
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor5() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect2", -1.0, 1.0, 1.0, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor6() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect2", 1.0, -1.0, 1.0, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidChangeColor7() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.changeColor("rect2", 1.0, 1.0, -1.0, 20, 30);
//  }
//
//  @Test
//  public void TestScale() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//    single.scale("1", 2, 3, 10, 50);
//
//    expectedId = new String[]{"1"};
//    expectedPosition = new String[]{"(1.0,2.0)"};
//    expectedColor = new String[]{"(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Oval"};
//    expectedSize = new String[]{"2.3 3.3"};
//    i = 0;
//
//    singleImg = single.getAllShapeByTic(40);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.scale("oval1", 100, 120, 20, 30);
//    multiple1.scale("rect1", 10, 20, 70, 80);
//    multiple1.scale("rect2", 11, 21, 70, 80);
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"6.5 12.0", "6.5 11.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(75);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.scale("rect2", 2, 21, 5, 50);
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"9.3 18.4", "10.1 21.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(79);
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//  }
//
//  // invalid ID
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale1() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale(null, 100, 100, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale2() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale("rect", 100, 100, 20, 30);
//  }
//
//  // invalid fromTime or toTime
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale3() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale("rect2", 100, 100, -1, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale4() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale("rect2", 100, 100, 20, 230);
//  }
//
//  // invalid position
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale5() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale("rect2", 100, -1000, 20, 30);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidScale6() {
//    multiple2.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple2.scale("rect2", -100, 100, 20, 30);
//  }
//
//  @Test
//  public void TestGetShapeByTic() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    List<Image> singleImg = new ArrayList<>();
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    expectedId = new String[]{"1"};
//    expectedPosition = new String[]{"(1.0,2.0)"};
//    expectedColor = new String[]{"(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Oval"};
//    expectedSize = new String[]{"3.0 4.0"};
//    i = 0;
//
//    singleImg.add(single.getShapeByTic("1", 29));
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//    singleImg.clear();
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    expectedId = new String[]{"rect1", "rect2", "oval1"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)", "(1.0,2.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)",
//        "(0.500, 0.100, 0.200)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle", "Oval"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0", "3.0 4.0"};
//    i = 0;
//
//    singleImg.add(multiple1.getShapeByTic("rect1", 50));
//    singleImg.add(multiple1.getShapeByTic("rect2", 50));
//    singleImg.add(multiple1.getShapeByTic("oval1", 50));
//
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//    singleImg.clear();
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg.add(multiple1.getShapeByTic("rect1", 79));
//    singleImg.add(multiple1.getShapeByTic("rect2", 79));
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//  }
//
//  // id does not exist
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetShapeByTic1() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getShapeByTic("oval", 20);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetShapeByTic2() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getShapeByTic(null, 20);
//  }
//
//  // time is not valid
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetShapeByTic3() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getShapeByTic("oval1", 52);
//  }
//
//  @Test
//  public void TestGetAllShapeByTic() {
//    String[] expectedId;
//    String[] expectedPosition;
//    String[] expectedColor;
//    String[] expectedShape;
//    String[] expectedSize;
//    Iterable<Image> singleImg;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    i = 0;
//    single.removeElement("1");
//    singleImg = single.getAllShapeByTic(29);
//    for (Image img : singleImg) {
//      assertNull(img);
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.removeElement("oval1");
//
//    expectedId = new String[]{"rect1", "rect2"};
//    expectedPosition = new String[]{"(1.0,2.0)", "(1.0,21.0)"};
//    expectedColor = new String[]{"(0.200, 0.100, 0.920)", "(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle", "Rectangle"};
//    expectedSize = new String[]{"3.0 4.0", "2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(50);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    multiple1.removeElement("rect1");
//
//    expectedId = new String[]{"rect2"};
//    expectedPosition = new String[]{"(1.0,21.0)"};
//    expectedColor = new String[]{"(0.120, 0.110, 0.920)"};
//    expectedShape = new String[]{"Rectangle"};
//    expectedSize = new String[]{"2.0 1.0"};
//    i = 0;
//
//    singleImg = multiple1.getAllShapeByTic(79);
//    for (Image img : singleImg) {
//      assertEquals(expectedId[i], img.getId());
//      assertEquals(expectedPosition[i], img.getPosition().toString());
//      assertEquals(expectedColor[i], img.getColor().toString());
//      assertEquals(expectedShape[i], img.getShape().toString());
//      assertEquals(expectedSize[i], img.getSize().toString());
//      i += 1;
//    }
//
//    i = 0;
//    multiple1.removeElement("rect2");
//    singleImg = multiple1.getAllShapeByTic(29);
//    for (Image img : singleImg) {
//      assertNull(img);
//      i += 1;
//    }
//  }
//
//  // time is invalid
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetAllShapeByTic() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getShapeByTic("oval1", 52);
//  }
//
//  @Test
//  public void TestGetLog() {
//    String[] expected;
//    int i;
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    single.scale("1", 2, 3, 10, 50);
//    single.move("1", 2, 3, 20, 70);
//
//    expected = new String[]{"Shape 1 moves from (1.0,2.0) to "
//        + "(2.0,3.0) from t=20 to t=70", "Shape 1 scales from X radius: 3.0, "
//        + "Y radius: 4.0 to X radius:"
//        + " 2.0, Y radius: 3.0 from t=10 to t=50"};
//    i = 0;
//
//    for (LogNode log : single.getLog("1")) {
//      assertEquals(expected[i], log.getInfo());
//      i += 1;
//    }
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.changeColor("oval1", 0.99, 1.0, 0.2, 30, 50);
//    multiple1.changeColor("rect1", 1, 0.12, 0.7, 80, 90);
//    multiple1.changeColor("rect2", 0.1, 0.21, 0.7, 5, 80);
//
//    expected = new String[]{"Shape oval1 changes color from (0.500, 0.100, 0.200) to "
//        + "(0.990, 1.000, 0.200) from t=30 to t=50"};
//    i = 0;
//
//    for (LogNode log : multiple1.getLog("oval1")) {
//      assertEquals(expected[i], log.getInfo());
//      i += 1;
//    }
//
//    expected = new String[]{"Shape rect1 changes color from (0.200, 0.100, 0.920) to "
//        + "(1.000, 0.120, 0.700) from t=80 to t=90"};
//    i = 0;
//
//    for (LogNode log : multiple1.getLog("rect1")) {
//      assertEquals(expected[i], log.getInfo());
//      i += 1;
//    }
//
//    expected = new String[]{"Shape rect2 changes color from (0.120, 0.110, 0.920) to "
//        + "(0.100, 0.210, 0.700) from t=5 to t=80"};
//    i = 0;
//
//    for (LogNode log : multiple1.getLog("rect2")) {
//      assertEquals(expected[i], log.getInfo());
//      i += 1;
//    }
//  }
//
//  // ID is invalid
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetLog1() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getLog("oval");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void TestInvalidGetLog2() {
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.getLog(null);
//  }
//
//  @Test
//  public void TestToString() {
//    String expected = "Shapes:\n"
//        + "Name: 1\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,2.0), X radius: 3.0, Y radius: 4.0, Color: (0.500, 0.100, 0.200)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 100\n\n"
//        + "Shape 1 scales from X radius: 3.0, Y radius: 4.0 to X radius: 2.0, "
//        + "Y radius: 3.0 from t=10 to t=50\n"
//        + "Shape 1 moves from (1.0,2.0) to (2.0,3.0) from t=20 to t=70\n";
//
//    single.addElement("1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 100);
//
//    single.scale("1", 2, 3, 10, 50);
//    single.move("1", 2, 3, 20, 70);
//
//    assertEquals(expected, single.toString());
//
//    multiple1.addElement("oval1", 1, 2, 0.5, 0.1, 0.2,
//        Shape.Oval, 3, 4, 0, 51);
//    multiple1.addElement("rect1", 1, 2, 0.2, 0.1, 0.92,
//        Shape.Rectangle, 3, 4, 50, 100);
//    multiple1.addElement("rect2", 1, 21, 0.12, 0.11, 0.92,
//        Shape.Rectangle, 2, 1, 3, 80);
//
//    multiple1.changeColor("oval1", 0.99, 1.0, 0.2, 30, 50);
//    multiple1.scale("oval1", 2, 3, 30, 50);
//    multiple1.changeColor("rect1", 1, 0.12, 0.7, 80, 90);
//    multiple1.changeColor("rect2", 0.1, 0.21, 0.7, 5, 80);
//    multiple1.removeElement("rect2");
//
//    expected = "Shapes:\n"
//        + "Name: rect1\n"
//        + "Type: Rectangle\n"
//        + "Min Corner: (1.0,2.0), Width: 3.0, Height: 4.0, Color: (0.200, 0.100, 0.920)\n"
//        + "Appears at t= 50\n"
//        + "Disappears at t= 100\n"
//        + "\n"
//        + "Name: oval1\n"
//        + "Type: Oval\n"
//        + "Center: (1.0,2.0), X radius: 3.0, Y radius: 4.0, Color: (0.500, 0.100, 0.200)\n"
//        + "Appears at t= 0\n"
//        + "Disappears at t= 51\n"
//        + "\n"
//        + "Shape oval1 changes color from (0.500, 0.100, 0.200) to (0.990, 1.000, 0.200) "
//        + "from t=30 to t=50\n"
//        + "Shape oval1 scales from X radius: 3.0, Y radius: 4.0 to X radius: 2.0, Y radius: 3.0 "
//        + "from t=30 to t=50\n"
//        + "Shape rect1 changes color from (0.200, 0.100, 0.920) to (1.000, 0.120, 0.700) "
//        + "from t=80 to t=90\n";
//
//    assertEquals(expected, multiple1.toString());
//
//  }
//
//}
