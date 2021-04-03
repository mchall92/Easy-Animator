import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements window, it's a concrete window users can use to generate animation.
 */
public class WindowImpl implements Window{

    private HashMap<String, Element> elements;
    private Deque<String> priorities;
    private int width;
    private int height;

    /**
     * Initialize the window with a user-defined size.
     * @param width width of the window
     * @param height height of the window
     */
    public WindowImpl(int width, int height) {
        this.elements = new HashMap<>();
        this.priorities = new LinkedList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Check whether position is out of board, if not, throw exception.
     * @param x
     * @param y
     */
    private void checkOutOfBoard(double x, double y) {
        if (x < 0 || x > width || y < 0 || y > height) {
            throw new IllegalArgumentException("Target position is out of board");
        }
    }

    /**
     * Check whether there is corresponding element to the id, if not, throw exception.
     * @param id id given to find the element
     */
    private void checkExist(String id) {
        if (!elements.containsKey(id)) {
            throw new IllegalArgumentException("No element to remove!");
        }
    }

    /**
     * Check whether fromTime is smaller than toTime, if not, throw exception.
     * @param fromTime
     * @param toTime
     */
    private void checkTimeSequence(int fromTime, int toTime) {
        if (fromTime >= toTime) {
            throw new IllegalArgumentException("fromTime should be smaller than toTime");
        }
    }

    @Override
    public void addElement(String id, double x, double y, double r, double g, double b,
                           Shape shape, double sizeArg1, double sizeArg2,
                           int appearTime, int disappearTime) {
        checkOutOfBoard(x, y);
        priorities.addLast(id);
        Element ele = new ElementImpl(id, new Position(x, y), new Color(r, g, b),
                shape, new Size(sizeArg1, sizeArg2), appearTime, disappearTime);
        elements.put(id, ele);
    }

    @Override
    public void removeElement(String id) {
        checkExist(id);
        elements.remove(id);
        priorities.remove(id);
    }

    @Override
    public void move(String id, double toX, double toY, int fromTime, int toTime) {
        checkOutOfBoard(toX, toY);
        checkTimeSequence(fromTime, toTime);
        checkExist(id);
        elements.get(id).move(new Position(toX, toY), fromTime, toTime);
    }

    @Override
    public void changeColor(String id, double r, double g, double b, int fromTime, int toTime) {
        checkTimeSequence(fromTime, toTime);
        checkExist(id);
        elements.get(id).changeColor(new Color(r, g, b), fromTime, toTime);
    }

    @Override
    public void scale(String id, double first, double second, int fromTime, int toTime) {
        checkTimeSequence(fromTime, toTime);
        checkExist(id);
        elements.get(id).changeSize(new Size(first, second), fromTime, toTime);
    }

    @Override
    public void increasePriorityToHighest(String id) {
        checkExist(id);
        priorities.remove(id);
        priorities.addLast(id);
    }

    @Override
    public void decreasePriorityToLowest(String id) {
        checkExist(id);
        priorities.remove(id);
        priorities.addFirst(id);
    }

    @Override
    public Image getShapeByTic(String id, int time) {
        return elements.get(id).getAtTic(time);
    }

    @Override
    public Iterable<Image> getAllShapeByTic(int time) {
        return elements.values().stream().
                filter((x) -> time >= x.getAppearTime() && time <= x.getDisappearTime()).
                map((x) -> x.getAtTic(time)).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<LogNode> getLog(String id) {
        return elements.get(id).generateLog();
    }

    /**
     * First shows basic information of every element.
     * Second shows information of transformation in order of time.
     * @return The string which represents this window
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<LogNode> transLog = new ArrayList<>();
        for (Element e : elements.values()) {
            transLog.addAll(e.generateLog());
        }
        transLog.sort(Comparator.comparingInt(LogNode::getTime));
        sb.append("Shapes:\n");
        for (Element e : elements.values()) {
            sb.append(e);
        }
        for (LogNode l : transLog) {
            sb.append(l.getInfo());
        }
        return sb.toString();
    }
}
