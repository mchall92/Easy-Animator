import java.util.*;
import java.util.stream.Collectors;

public class WindowImpl implements Window{

    private HashMap<String, Element> elements;
    private Deque<String> priorities;
    private int width;
    private int height;
    private StringBuilder log;
    private ArrayList<Node> actions;

    public WindowImpl(int width, int height) {
        this.elements = new HashMap<>();
        this.priorities = new LinkedList<>();
        this.width = width;
        this.height = height;
        this.log = new StringBuilder();
        this.actions = new ArrayList<>();
        log.append("Shapes:\n");
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
    public void addElement(int x, int y, Color color, String id, int appearTime, int disappearTime) {
        checkOutOfBoard(x, y);
        priorities.addLast(id);
        Element ele = new ElementImpl(id, x, y, color, appearTime, disappearTime);
        elements.put(id, ele);
        log.append("Name: " + id + "\n");
        log.append(ele.toString());
    }

    @Override
    public void removeElement(String id) {
        checkExist(id);
        elements.remove(id);
        priorities.remove(id);
    }

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
    public String toString() {

    }
}
