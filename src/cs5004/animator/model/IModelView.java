package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the interface for windowImpl to implement getter methods
 * for adaptation.
 */
public interface IModelView {
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
   * Return the end time of this animation.
   *
   * @return the end time of this animation.
   */
  public int getEndTime();

  /**
   * First shows basic information of every element. Second shows information of transformation in
   * order of time.
   *
   * @return The string which represents this window
   */
  String toString();

  /** Get all shapes in order of priority. New added. */
  List<Element> getPriorities();

  /**
   * Return a list of element IDs.
   * @return a list of element IDs.
   */
  public List<String> getElementIDs();
}
