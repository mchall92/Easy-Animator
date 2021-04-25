package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;

import static cs5004.animator.model.SvgHelper.animationSvgGenerator;

/**
 * This class implements Element, it represents an element in a window. This class uses different
 * sequences to record transformation of different features to avoid conflict.
 */
public class ElementImpl implements Element {

  private String id;
  private Position position;
  private ModelColor color;
  private Shape shape;
  private Size size;
  private ArrayList<Transformation> moveSchedule;
  private ArrayList<Transformation> colorSchedule;
  private ArrayList<Transformation> scaleSchedule;
  private int appearTime;
  private int disappearTime;
  private String svgMotion; // new added
  private ArrayList<LogNode> log;

  /**
   * This constructor constructs the original features of this element.
   *
   * @param id The name of the element
   * @param position the position of the element
   * @param color the color of the element
   * @param size the size of the element
   * @param shape the shape of the element
   * @param appearTime the appear time of the element
   * @param disappearTime the disappear time of the element
   */
  public ElementImpl(
      String id,
      Shape shape,
      Position position,
      ModelColor color,
      Size size,
      int appearTime,
      int disappearTime) {
    this.id = id;
    this.shape = shape;
    this.position = position;
    this.color = color;
    this.size = size;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    moveSchedule = new ArrayList<>();
    colorSchedule = new ArrayList<>();
    scaleSchedule = new ArrayList<>();
    this.svgMotion = "";
    this.log = new ArrayList<>();
  }

  /**
   * Check whether time points are in the periods when element exists, if not, throw exception.
   *
   * @param fromTime fromTime of the motion
   * @param toTime toTime of the motion
   */
  private void checkTime(int fromTime, int toTime) {
    if (fromTime < appearTime || toTime > disappearTime) {
      throw new IllegalArgumentException("Cannot do this before appearing or after disappearing");
    }
  }

  /**
   * Check whether time points overlap with existing time schedule, if not, throw exception.
   *
   * @param fromTime fromTime of the motion
   * @param toTime toTime of the motion
   */
  private void checkTimeOverLapped(int fromTime, int toTime, int currFromTime, int currToTime) {
    int[] tmp = new int[] {fromTime, toTime, currFromTime, currToTime};
    OptionalInt max1 = Arrays.stream(tmp).max();
    OptionalInt min1 = Arrays.stream(tmp).min();
    int max = max1.getAsInt();
    int min = min1.getAsInt();
    if (max - min < toTime - fromTime + currFromTime - currToTime) {
      throw new IllegalArgumentException("Time Overlapped!");
    }
  }

  /**
   * Check whether a move transformation has time conflict.
   *
   * @param fromTime starting time of this transformation
   * @param toTime ending time of this transformation
   */
  private void checkTimeOverLappedMove(int fromTime, int toTime) {
    for (Transformation time : moveSchedule) {
      checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
      if (toTime <= time.getFromTime()) {
        return;
      }
    }
  }

  /**
   * Check whether a change color transformation has time conflict.
   *
   * @param fromTime starting time of this transformation
   * @param toTime ending time of this transformation
   */
  private void checkTimeOverLappedColor(int fromTime, int toTime) {
    for (Transformation time : colorSchedule) {
      checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
      if (toTime <= time.getFromTime()) {
        return;
      }
    }
  }

  /**
   * Check whether a change size transformation has time conflict.
   *
   * @param fromTime starting time of this transformation
   * @param toTime ending time of this transformation
   */
  private void checkTimeOverLappedScale(int fromTime, int toTime) {
    for (Transformation time : scaleSchedule) {
      checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
      if (toTime <= time.getFromTime()) {
        return;
      }
    }
  }

  /**
   * The getter of the position of this element at a specific time. Use a linear equation to work
   * out the accurate position if the element is transforming at the time.
   *
   * @param time specific time
   * @return position at that time
   */
  private Position getPosByTic(int time) {
    Position curr = this.position;
    for (Transformation t : moveSchedule) {
      if (time >= t.getToTime()) {
        curr = (Position) t.getItem();
      } else if (time >= t.getFromTime()) {
        Position tmp = (Position) t.getItem();
        double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
        int x = (int) (curr.getX() + (tmp.getX() - curr.getX()) * percentage);
        int y = (int) (curr.getY() + (tmp.getY() - curr.getY()) * percentage);
        return new Position(x, y);
      } else {
        return curr;
      }
    }
    return curr;
  }

  /**
   * The getter of the color of this element at a specific time. Use a linear equation to work out
   * the accurate color if the element is transforming at the time.
   *
   * @param time specific time
   * @return color at that time
   */
  private ModelColor getColorByTic(int time) {
    ModelColor curr = color;
    for (Transformation t : colorSchedule) {
      if (time >= t.getToTime()) {
        curr = (ModelColor) t.getItem();
      } else if (time >= t.getFromTime()) {
        ModelColor tmp = (ModelColor) t.getItem();
        double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
        int r = (int) (curr.getR() + (tmp.getR() - curr.getR()) * percentage);
        int g = (int) (curr.getG() + (tmp.getG() - curr.getG()) * percentage);
        int b = (int) (curr.getB() + (tmp.getB() - curr.getB()) * percentage);
        return new ModelColor(r, g, b);
      } else {
        return curr;
      }
    }
    return curr;
  }

  /**
   * The getter of the size of this element at a specific time. Use a linear equation to work out
   * the accurate size if the element is transforming at the time.
   *
   * @param time specific time
   * @return size at that time
   */
  private Size getSizeByTic(int time) {
    Size curr = size;
    for (Transformation t : scaleSchedule) {
      if (time >= t.getToTime()) {
        curr = (Size) t.getItem();
      } else if (time >= t.getFromTime()) {
        Size tmp = (Size) t.getItem();
        double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
        int one =
            (int) (curr.getFirstArg() + (tmp.getFirstArg() - curr.getFirstArg()) * percentage);
        int two =
            (int) (curr.getSecondArg() + (tmp.getSecondArg() - curr.getSecondArg()) * percentage);
        return new Size(one, two);
      } else {
        return curr;
      }
    }
    return curr;
  }

  /**
   * This method is a helper to generate text log and svg log. This method is new added.
   *
   * @param speed The playing speed customized by the user
   */
  private void changeLogs(int speed) {
    log.clear();
    svgMotion = "";
    Position curr1 = position;
    for (Transformation t : moveSchedule) {
      Position tmp = (Position) t.getItem();
      String s =
          String.format(
              "Shape %s moves from %s to" + " %s from t=%d to t=%d\n",
              id, curr1, tmp, t.getFromTime(), t.getToTime());
      log.add(new LogNode(t.getFromTime(), s));
      String svg =
          animationSvgGenerator(
                  shape.getSvgPosTitle1(),
                  curr1.getX(),
                  tmp.getX(),
                  ((double) t.getFromTime()) / speed,
                  ((double) t.getToTime()) / speed)
              + animationSvgGenerator(
                  shape.getSvgPosTitle2(),
                  curr1.getY(),
                  tmp.getY(),
                  ((double) t.getFromTime()) / speed,
                  ((double) t.getToTime()) / speed);
      svgMotion += svg;
      curr1 = tmp;
    }

    ModelColor curr2 = color;
    for (Transformation t : colorSchedule) {
      ModelColor tmp = (ModelColor) t.getItem();
      String s =
          String.format(
              "Shape %s changes color from %s to" + " %s from t=%d to t=%d\n",
              id, curr2, tmp, t.getFromTime(), t.getToTime());
      log.add(new LogNode(t.getFromTime(), s));
      String svg =
          String.format(
              "<animate attributeName=\"fill\" from=\"%s\" "
                  + "to=\"%s\" begin=\"%.1fs\" end=\"%.1fs\" dur=\"%.1f\" fill=\"freeze\"/>\n",
              curr2.toSvgString(),
              tmp.toSvgString(),
              ((double) t.getFromTime()) / speed,
              ((double) t.getToTime()) / speed,
              ((double) (t.getToTime() - t.getFromTime())) / speed);
      svgMotion += svg;
      curr2 = tmp;
    }

    Size curr3 = size;
    for (Transformation t : scaleSchedule) {
      Size tmp = (Size) t.getItem();
      String s =
          String.format(
              "Shape %s scales from %s: %.1f, %s: %.1f to"
                  + " %s: %.1f, %s: %.1f from t=%d to t=%d\n",
              id,
              shape.getSizeTitle1(),
              curr3.getFirstArg(),
              shape.getSizeTitle2(),
              curr3.getSecondArg(),
              shape.getSizeTitle1(),
              tmp.getFirstArg(),
              shape.getSizeTitle2(),
              tmp.getSecondArg(),
              t.getFromTime(),
              t.getToTime());
      log.add(new LogNode(t.getFromTime(), s));
      String svg =
          animationSvgGenerator(
                  shape.getSvgSizeTitle1(),
                  curr3.getFirstArg(),
                  tmp.getSecondArg(),
                  ((double) t.getFromTime()) / speed,
                  ((double) t.getToTime()) / speed)
              + animationSvgGenerator(
                  shape.getSvgSizeTitle2(),
                  curr3.getFirstArg(),
                  tmp.getSecondArg(),
                  ((double) t.getFromTime()) / speed,
                  ((double) t.getToTime()) / speed);
      svgMotion += svg;
      curr3 = tmp;
    }
  }

  // abstract the function body out into the changeLogs method.
  @Override
  public ArrayList<LogNode> generateLog() {
    changeLogs(1);
    return log;
  }

  // new added
  @Override
  public String getSvgMotion(int speed) {
    changeLogs(speed);
    return svgMotion;
  }

  @Override
  public void move(Position pos, int fromTime, int toTime) {
    checkTime(fromTime, toTime);
    checkTimeOverLappedMove(fromTime, toTime);
    moveSchedule.add(new Transformation(fromTime, toTime, pos));
    moveSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
  }

  @Override
  public void changeColor(ModelColor color, int fromTime, int toTime) {
    checkTime(fromTime, toTime);
    checkTimeOverLappedColor(fromTime, toTime);
    colorSchedule.add(new Transformation(fromTime, toTime, color));
    colorSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
  }

  @Override
  public void changeSize(Size size, int fromTime, int toTime) {
    checkTime(fromTime, toTime);
    checkTimeOverLappedScale(fromTime, toTime);
    scaleSchedule.add(new Transformation(fromTime, toTime, size));
    scaleSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
  }

  @Override
  public int getAppearTime() {
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  @Override
  public Image getAtTic(int time) {
    Position pos = getPosByTic(time);
    ModelColor col = getColorByTic(time);
    Size size = getSizeByTic(time);
    return new Image(id, pos, col, shape, size);
  }

  /**
   * The sample format of the feature string is:
   *
   * <p>Shapes: Name: R Type: rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,
   * Color: (1.0,0.0,0.0) Appears at t=1 Disappears at t=100
   *
   * <p>Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color:
   * (0.0,0.0,1.0) Appears at t=6 Disappears at t=100
   *
   * @return
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Type: " + shape + "\n");
    sb.append(shape.getPosTitle() + ": " + position + ", ");
    sb.append(shape.getSizeTitle1() + ": " + size.getFirstArg() + ", ");
    sb.append(shape.getSizeTitle2() + ": " + size.getSecondArg() + ", ");
    sb.append("Color: " + color + " ");
    sb.append(String.format("Appears at t=%d ", appearTime));
    sb.append(String.format("Disappears at t=%d\n", disappearTime));
    return sb.toString();
  }

  // new added
  @Override
  public String svgShape(int speed) {
    StringBuilder sb = new StringBuilder();
    sb.append("<" + shape.getSvgType() + " ");
    sb.append(
        SvgHelper.assignment(Integer.toString(position.getX()), shape.getSvgPosTitle1()) + " ");
    sb.append(
        SvgHelper.assignment(Integer.toString(position.getY()), shape.getSvgPosTitle2()) + " ");
    sb.append(
        SvgHelper.assignment(Integer.toString(size.getFirstArg()), shape.getSvgSizeTitle1()) + " ");
    sb.append(
        SvgHelper.assignment(Integer.toString(size.getSecondArg()), shape.getSvgSizeTitle2())
            + " ");
    sb.append(SvgHelper.assignment(color.toSvgString(), "fill") + " ");
    sb.append(SvgHelper.assignment("hidden", "visibility"));
    sb.append(">\n");
    sb.append(
        String.format(
            "<animate attributeName=\"visibility\" " + "to=\"visible\" begin=\"%ds\"/>\n",
            appearTime / speed));
    sb.append(
        String.format(
            "<animate attributeName=\"visibility\" " + "to=\"hidden\" begin=\"%ds\"/>\n",
            disappearTime / speed));
    sb.append(getSvgMotion(speed));
    sb.append("</" + shape.getSvgType() + ">\n");
    return sb.toString();
  }

  /**
   * Return element shape.
   *
   * @return Return element shape.
   */
  @Override
  public Shape getShape() {
    return this.shape;
  }
}
