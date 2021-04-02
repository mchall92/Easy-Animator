import java.awt.*;

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
}
