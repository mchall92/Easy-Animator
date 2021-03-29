import java.awt.*;

public interface Window {
    /**
     * Add an Element to the window at position.
     * @param x x coordinate
     * @param y y coordinate
     * @param color color of the new element
     * @param id The id of the element
     * @param appearTime At which time this element appears
     * @param disappearTime At which time this element disappears
     */
    void addElement(int x, int y, Color color, String id, int appearTime, int disappearTime);

    /**
     * Remove an Element of a specific id.
     * @param id id of Element
     */
    void removeElement(String id);

    /**
     * User-oriented version of move method.
     * Move an Element of a specific id to another position.
     * @param id id of the element
     * @param toX target x coordinate
     * @param toY target y coordinate
     * @param fromTime original time
     * @param toTime original time
     */
    void move(String id, int toX, int toY, int fromTime, int toTime);

    /**
     * Change the color of an Element.
     * @param id id of the element
     * @param color target color
     * @param fromTime original time
     * @param toTime original time
     */
    void changeColor(String id, Color color, int fromTime, int toTime);

    /**
     * Increase the priority of the Element to highest.
     * @param id id of Element
     */
    void increasePriorityToHighest(String id);

    /**
     * Decrease the priority of the Element to lowest.
     * @param id id of Element
     */
    void decreasePriorityToLowest(String id);
}
