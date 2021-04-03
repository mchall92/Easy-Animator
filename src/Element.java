import java.util.ArrayList;

/**
 * This is a programmer-oriented interface of the animator.
 * This represents an element in a window.
 * An element has several fields, including: id, position, color, size, shape.
 * Also, It's possible to add animation to this element, which happens in a period of time.
 * There is also a method to get image at a specific time.
 * This element is able to generate log of information related to itself.
 */
public interface Element {
    /**
     * Developer-oriented method of move.
     * @param pos target position
     * @param fromTime original time
     * @param toTime original time
     * return log of this moving action
     */
    void move(Position pos, int fromTime, int toTime);

    /**
     * Developer-oriented method of changeColor.
     * @param color target color
     * @param fromTime original time
     * @param toTime original time
     * return log of this changing action
     */
    void changeColor(Color color, int fromTime, int toTime);

    /**
     * Developer-oriented method of changeSize.
     * @param size target size
     * @param fromTime original time
     * @param toTime original time
     * return log of this changing action
     */
    void changeSize(Size size, int fromTime, int toTime);

    /**
     * Getter of appear time.
     * @return appear time
     */
    int getAppearTime();

    /**
     * Getter of disappear time.
     * @return disappear time
     */
    int getDisappearTime();

    /**
     * Get a image of the element at a specific time.
     * @param time specific time
     * @return image at given time
     */
    Image getAtTic(int time);

    /**
     * generate a log about all transformations about this element.
     * @return a sequence of log
     */
    ArrayList<LogNode> generateLog();
}
