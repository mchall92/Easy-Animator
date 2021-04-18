package cs5004.animator.model;

import java.util.*;
import java.util.stream.Collectors;

/** This class implements window, it's a concrete window users can use to generate animation. */
public class WindowImpl implements Window {

  private HashMap<String, Element> elements;
  private List<String> priorities;
  private int lowestX;
  private int lowestY;
  private int highestX;
  private int highestY;
  private int width;
  private int height;

  /**
   * Initialize the window with a user-defined size.
   *
   * @param x leftmost x value of the window(newly added)
   * @param y top y value of the window(newly added)
   * @param width width of the window
   * @param height height of the window
   */
  public WindowImpl(int x, int y, int width, int height, List<String> priorities) {
    this.elements = new HashMap<>();
    this.priorities = priorities;
    this.lowestX = x;
    this.lowestY = y;
    this.highestX = x + width;
    this.highestY = y + height;
    this.width = width;
    this.height = height;
  }

  /**
   * Check whether position is out of board, if not, throw exception.
   *
   * @param x x coordinate of the element
   * @param y y coordinate of the element
   */
  private void checkOutOfBoard(double x, double y) {
    if (x < lowestX || x > highestX || y < lowestY || y > highestY) {
      throw new IllegalArgumentException("Target position is out of board");
    }
  }

  /**
   * Check whether there is corresponding element to the id, if not, throw exception.
   *
   * @param id id given to find the element
   */
  private void checkExist(String id) {
    if (!elements.containsKey(id)) {
      throw new IllegalArgumentException("No element to remove!");
    }
  }

  /**
   * Check whether fromTime is smaller than toTime, if not, throw exception.
   *
   * @param fromTime fromTime of the motion
   * @param toTime toTime of the motion
   */
  private void checkTimeSequence(int fromTime, int toTime) {
    if (fromTime >= toTime) {
      throw new IllegalArgumentException("fromTime should be smaller than toTime");
    }
  }

  @Override
  public void addElement(
      String id,
      int x,
      int y,
      int r,
      int g,
      int b,
      Shape shape,
      int sizeArg1,
      int sizeArg2,
      int appearTime,
      int disappearTime) {
    //        checkOutOfBoard(x, y);
    Element ele =
        new ElementImpl(
            id,
            shape,
            new Position(x, y),
            new Color(r, g, b),
            new Size(sizeArg1, sizeArg2),
            appearTime,
            disappearTime);
    elements.put(id, ele);
  }

  @Override
  public void removeElement(String id) {
    checkExist(id);
    elements.remove(id);
    priorities.remove(id);
  }

  @Override
  public void move(String id, int toX, int toY, int fromTime, int toTime) {
    //        checkOutOfBoard(toX, toY);
    checkTimeSequence(fromTime, toTime);
    checkExist(id);
    elements.get(id).move(new Position(toX, toY), fromTime, toTime);
  }

  @Override
  public void changeColor(String id, int r, int g, int b, int fromTime, int toTime) {
    checkTimeSequence(fromTime, toTime);
    checkExist(id);
    elements.get(id).changeColor(new Color(r, g, b), fromTime, toTime);
  }

  @Override
  public void scale(String id, int first, int second, int fromTime, int toTime) {
    checkTimeSequence(fromTime, toTime);
    checkExist(id);
    elements.get(id).changeSize(new Size(first, second), fromTime, toTime);
  }

  @Override
  public Image getShapeByTic(String id, int time) {
    return elements.get(id).getAtTic(time);
  }

  // Changed to use priorities to make visual view able to arrange priorities of shapes.
  @Override
  public Iterable<Image> getAllShapeByTic(int time) {
    List<Element> tmp = getPriorities();
    return tmp.stream()
        .filter((x) -> time >= x.getAppearTime() && time <= x.getDisappearTime())
        .map((x) -> x.getAtTic(time))
        .collect(Collectors.toList());
  }

  @Override
  public ArrayList<LogNode> getLog(String id) {
    return elements.get(id).generateLog();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ArrayList<LogNode> transLog = new ArrayList<>();
    for (Element e : elements.values()) {
      transLog.addAll(e.generateLog());
    }
    transLog.sort(Comparator.comparingInt(LogNode::getTime));
    sb.append("Shapes:\n");
    for (Element e : getPriorities()) {
      sb.append(e);
    }
    for (LogNode l : transLog) {
      sb.append(l.getInfo());
    }
    return sb.toString();
  }

  @Override
  public List<Element> getPriorities() {
    return priorities.stream().map((x) -> elements.get(x)).collect(Collectors.toList());
  }

  @Override
  public String toSvgString(int speed) {
    StringBuilder sb = new StringBuilder();
    sb.append(
        String.format(
            "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" "
                + "width=\"%dpx\" height=\"%dpx\" viewBox=\"%d %d %d %d\">\n",
            width, height, lowestX, lowestY, highestX, highestY));
    List<Element> tmp = getPriorities();
    for (Element e : tmp) {
      sb.append(e.svgShape(speed));
    }
    sb.append("</svg>");
    return sb.toString();
  }
}
