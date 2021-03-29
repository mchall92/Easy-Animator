import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;

public class ElementImpl implements Element{
    private class Node {
        private int fromTime;
        private int toTime;

        public Node(int fromTime, int toTime) {
            this.fromTime = fromTime;
            this.toTime = toTime;
        }
    }

    private String id;
    private int x;
    private int y;
    private Color color;
    private Shape shape;
    private ArrayList<Node> moveSchedule;
    private ArrayList<Node> colorSchedule;
    private int appearTime;
    private int disappearTime;

    public ElementImpl(String id, int x, int y, Color color, int appearTime, int disappearTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.color = color;
        this.appearTime = appearTime;
        this.disappearTime = disappearTime;
        moveSchedule = new ArrayList<>();
        colorSchedule = new ArrayList<>();
    }

    /**
     * Check whether time points are in the periods when element exists, if not, throw exception.
     * @param fromTime
     * @param toTime
     */
    private void checkTime(int fromTime, int toTime) {
        if (fromTime < appearTime || toTime > disappearTime) {
            throw new IllegalArgumentException("Cannot do this before appearing or after disappearing");
        }
    }

    /**
     * Check whether time points overlap with existing time schedule, if not, throw exception.
     * @param fromTime
     * @param toTime
     */
    private void checkTimeOverLapped(int fromTime, int toTime) {
        for (Node time : moveSchedule) {
            int[] tmp = new int[] {fromTime, toTime, time.fromTime, time.toTime};
            OptionalInt max1 = Arrays.stream(tmp).max();
            OptionalInt min1 = Arrays.stream(tmp).min();
            int max = max1.getAsInt();
            int min = min1.getAsInt();
            if (max - min < toTime - fromTime + time.toTime - time.fromTime) {
                throw new IllegalArgumentException("Time Overlapped!");
            }
        }
    }

    @Override
    public String move(int toX, int toY, int fromTime, int toTime) {
        checkTime(fromTime, toTime);
        checkTimeOverLapped(fromTime, toTime);
        String s = String.format("Shape %s moves from (%.1f,%.1f) to" +
                " (%.1f,%.1f) from t=%d to t=%d", id, x, y, toX, toY, fromTime, toTime);
        this.x = toX;
        this.y = toY;
        return s;
    }

    @Override
    public String changeColor(Color color, int fromTime, int toTime) {
        checkTime(fromTime, toTime);
        checkTimeOverLapped(fromTime, toTime);
        String s = String.format("Shape %s changes color from %s to" +
                " %s from t=%d to t=%d", id, this.color, color, fromTime, toTime);
        this.color = color;
        return s;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ");
        sb.append(shape);
        sb.append(String.format("Appears at t=%d", appearTime));
        sb.append(String.format("Disappears at t=%d", disappearTime));
        return sb.toString();
    }
}
