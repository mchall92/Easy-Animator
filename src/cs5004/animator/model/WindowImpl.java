package cs5004.animator.model;

import java.awt.SystemTray;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements window and IViewWindow, it's a concrete window users can use to generate
 * animation.
 */
public class WindowImpl implements Window, IModelView {

  private HashMap<String, Element> elements;
  private List<String> priorities;
  private int lowestX;
  private int lowestY;
  private int highestX;
  private int highestY;
  private int width;
  private int height;
  private int endTime;

  /**
   * Initialize the window with a user-defined size.
   *
   * @param x      leftmost x value of the window(newly added)
   * @param y      top y value of the window(newly added)
   * @param width  width of the window
   * @param height height of the window
   */
  public WindowImpl(int x, int y, int width, int height) {
    this.elements = new HashMap<>();
    this.priorities = new LinkedList<>();
    this.lowestX = x;
    this.lowestY = y;
    this.highestX = x + width;
    this.highestY = y + height;
    this.width = width;
    this.height = height;
    this.endTime = 0;
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
   * @param toTime   toTime of the motion
   */
  private void checkTimeSequence(int fromTime, int toTime) {
    if (fromTime >= toTime) {
      throw new IllegalArgumentException("From Time has to be smaller than To Time.");
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
    this.checkTimeSequence(appearTime, disappearTime);
    Element ele = new ElementImpl(
        id,
        shape,
        new Position(x, y),
        new ModelColor(r, g, b),
        new Size(sizeArg1, sizeArg2),
        appearTime,
        disappearTime
    );
    priorities.add(id);
    if (elements.containsKey(id)) {
      throw new IllegalArgumentException("ID already exists.");
    }
    this.elements.put(id, ele);
    if (disappearTime > this.endTime) {
      this.endTime = disappearTime;
    }
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
    elements.get(id).changeColor(new ModelColor(r, g, b), fromTime, toTime);
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

  /**
   * Return a list of element IDs and their shapes.
   *
   * @return a list of element IDs and their shapes.
   */
  @Override
  public HashMap<String, Shape> getElementIDAndShape() {
    HashMap<String, Shape> map = new HashMap<>();
    for (String s : elements.keySet()) {
      map.put(s, elements.get(s).getShape());
      System.out.println(s);
    }
    return map;
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

  /**
   * Return the end time of this animation.
   *
   * @return the end time of this animation.
   */
  @Override
  public int getEndTime() {
    return this.endTime;
  }
}
