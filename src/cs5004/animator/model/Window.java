package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the user-oriented interface of the animator.
 *
 * <p>User can use this class to define size of canvas, add and remove any element they want, add a
 * move animation, change-color animation or scale animation to any element.
 *
 * <p>Also, user can change the priority of showing of any element. User can get an image which is a
 * slice of an element at a specific time. User can call the string method to get a log of what
 * happened in the whole process.
 */
public interface Window {
  /**
   * Add an Element to the window at position.
   *
   * @param id The id of the element
   * @param x x coordinate
   * @param y y coordinate
   * @param r red percentage of color
   * @param g green percentage of color
   * @param b blue percentage of color
   * @param shape shape of the element
   * @param sizeArg1 direction1 size argument
   * @param sizeArg2 direction2 size argument
   * @param appearTime At which time this element appears
   * @param disappearTime At which time this element disappears
   */
  void addElement(
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
      int disappearTime);

  /**
   * Remove an Element of a specific id.
   *
   * @param id id of Element
   */
  void removeElement(String id);

  /**
   * User-oriented version of move method. Move an Element of a specific id to another position.
   *
   * @param id id of the element
   * @param toX target x coordinate
   * @param toY target y coordinate
   * @param fromTime original time
   * @param toTime original time
   */
  void move(String id, int toX, int toY, int fromTime, int toTime);

  /**
   * Change the color of an Element.
   *
   * @param id id of the element
   * @param r red arg
   * @param g green arg
   * @param b blue arg
   * @param fromTime original time
   * @param toTime original time
   */
  void changeColor(String id, int r, int g, int b, int fromTime, int toTime);

  /**
   * User-oriented version of scale method. Scale an Element of a specific id to a specific size.
   *
   * @param id id of the element
   * @param first first argument of size
   * @param second second argument of size
   * @param fromTime original time
   * @param toTime original time
   */
  void scale(String id, int first, int second, int fromTime, int toTime);

  //    /**
  //     * Increase the priority of the Element to highest.
  //     *
  //     * @param id id of Element
  //     */
  //    void increasePriorityToHighest(String id);
  //
  //    /**
  //     * Decrease the priority of the Element to lowest.
  //     *
  //     * @param id id of Element
  //     */
  //    void decreasePriorityToLowest(String id);

  /**
   * Get shape by tic.
   *
   * @param id id of Element
   * @param time specific time
   * @return element at a specific time
   */
  Image getShapeByTic(String id, int time);

  /**
   * Get all shapes by tic.
   *
   * @param time specific time
   * @return all elements at a specific time
   */
  Iterable<Image> getAllShapeByTic(int time);

  /**
   * get the transformation log of a specific element.
   *
   * @return the element's log
   */
  ArrayList<LogNode> getLog(String id);

  /**
   * Generte a string representation of this window in svg format. New added.
   *
   * @return
   */
  String toSvgString(int speed);

  /**
   * First shows basic information of every element. Second shows information of transformation in
   * order of time.
   *
   * @return The string which represents this window
   */
  String toString();

  /** Get all shapes in order of priority. New added. */
  List<Element> getPriorities();
}
