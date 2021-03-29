import java.awt.*;
import java.util.*;

public class WindowImpl implements Window{
    private class Node {
        private int fromTime;
        private String info;

        public Node(int fromTime, String info) {
            this.fromTime = fromTime;
            this.info = info;
        }
    }

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
    private void checkOutOfBoard(int x, int y) {
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

    @Override
    public void move(String id, int toX, int toY, int fromTime, int toTime) {
        checkOutOfBoard(toX, toY);
        checkTimeSequence(fromTime, toTime);
        checkExist(id);
        String info = elements.get(id).move(toX, toY, fromTime, toTime);
        Node action = new Node(fromTime, info);
        actions.add(action);
    }

    @Override
    public void changeColor(String id, Color color, int fromTime, int toTime) {
        checkTimeSequence(fromTime, toTime);
        checkExist(id);
        String info = elements.get(id).changeColor(color, fromTime, toTime);
        Node action = new Node(fromTime, info);
        actions.add(action);
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
    public String toString() {
        actions.sort(Comparator.comparingInt(o -> o.fromTime));
        for (Node action : actions) {
            log.append(action.info);
        }
        return log.toString();
    }
}
