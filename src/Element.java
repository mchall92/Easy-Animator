import java.awt.*;

public interface Element {
    /**
     * Developer-oriented method of move.
     * @param toX target x coordinate
     * @param toY target y coordinate
     * @param fromTime original time
     * @param toTime original time
     * return log of this moving action
     */
    String move(int toX, int toY, int fromTime, int toTime);

    /**
     * Developer-oriented method of changeColor.
     * @param color target color
     * @param fromTime original time
     * @param toTime original time
     * return log of this changing action
     */
    String changeColor(Color color, int fromTime, int toTime);
}
