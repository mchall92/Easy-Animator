import java.util.*;

/**
 * This class implements Element, it represents an element in a window.
 * This class uses different sequences to record transformation of different features to avoid conflict.
 */
public class ElementImpl implements Element{

    private String id;
    private Position position;
    private Color color;
    private Shape shape;
    private Size size;
    private ArrayList<Transformation> moveSchedule;
    private ArrayList<Transformation> colorSchedule;
    private ArrayList<Transformation> scaleSchedule;
    private int appearTime;
    private int disappearTime;
    private String[] sizeTitle;
    private String posTitle;


    /**
     * This constructor constructs the original features of this element
     * @param id The name of the element
     * @param position the position of the element
     * @param color the color of the element
     * @param shape the shape of the element
     * @param size the size of the element
     * @param appearTime the appear time of the element
     * @param disappearTime the disappear time of the element
     */
    public ElementImpl(String id, Position position, Color color,
                       Shape shape, Size size, int appearTime, int disappearTime) {
        this.id = id;
        this.position = position;
        this.color = color;
        this.shape = shape;
        this.size = size;
        this.appearTime = appearTime;
        this.disappearTime = disappearTime;
        moveSchedule = new ArrayList<>();
        colorSchedule = new ArrayList<>();
        scaleSchedule = new ArrayList<>();
        this.sizeTitle = shape.sizeTitle().split(" ");
        this.posTitle = shape.posTitle();
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
    private void checkTimeOverLapped(int fromTime, int toTime, int currFromTime, int currToTime) {
            int[] tmp = new int[] {fromTime, toTime, currFromTime, currToTime};
            OptionalInt max1 = Arrays.stream(tmp).max();
            OptionalInt min1 = Arrays.stream(tmp).min();
            int max = max1.getAsInt();
            int min = min1.getAsInt();
            if (max - min < toTime - fromTime + currFromTime - currToTime) {
                throw new IllegalArgumentException("Time Overlapped!");
            }
        }

    /**
     * Check whether a move transformation has time conflict.
     * @param fromTime starting time of this transformation
     * @param toTime ending time of this transformation
     */
    private void checkTimeOverLappedMove(int fromTime, int toTime) {
        for (Transformation time : moveSchedule) {
            checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
        }
    }

    /**
     * Check whether a change color transformation has time conflict.
     * @param fromTime starting time of this transformation
     * @param toTime ending time of this transformation
     */
    private void checkTimeOverLappedColor(int fromTime, int toTime) {
        for (Transformation time : colorSchedule) {
            checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
        }
    }

    /**
     * Check whether a change size transformation has time conflict.
     * @param fromTime starting time of this transformation
     * @param toTime ending time of this transformation
     */
    private void checkTimeOverLappedScale(int fromTime, int toTime) {
        for (Transformation time : scaleSchedule) {
            checkTimeOverLapped(fromTime, toTime, time.getFromTime(), time.getToTime());
        }
    }

    /**
     * The getter of the position of this element at a specific time.
     * Use a linear equation to work out the accurate position if the element is transforming at the time.
     * @param time specific time
     * @return position at that time
     */
    private Position getPosByTic(int time) {
        Position curr = this.position;
        for (Transformation t : moveSchedule) {
            if (time >= t.getToTime()) {
                curr = (Position) t.getItem();
            } else if (time >= t.getFromTime()) {
                Position tmp = (Position) t.getItem();
                double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
                double x = curr.getX() + (tmp.getX() - curr.getX()) * percentage;
                double y = curr.getY() + (tmp.getY() - curr.getY()) * percentage;
                return new Position(x, y);
            } else {
                return curr;
            }
        }
        return curr;
    }

    /**
     * The getter of the color of this element at a specific time.
     * Use a linear equation to work out the accurate color if the element is transforming at the time.
     * @param time specific time
     * @return color at that time
     */
    private Color getColorByTic(int time) {
        Color curr = color;
        for (Transformation t : colorSchedule) {
            if (time >= t.getToTime()) {
                curr = (Color) t.getItem();
            } else if (time >= t.getFromTime()) {
                Color tmp = (Color) t.getItem();
                double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
                double r = curr.getR() + (tmp.getR() - curr.getR()) * percentage;
                double g = curr.getG() + (tmp.getG() - curr.getG()) * percentage;
                double b = curr.getB() + (tmp.getB() - curr.getB()) * percentage;
                return new Color(r, g, b);
            } else {
                return curr;
            }
        }
        return curr;
    }

    /**
     * The getter of the size of this element at a specific time.
     * Use a linear equation to work out the accurate size if the element is transforming at the time.
     * @param time specific time
     * @return size at that time
     */
    private Size getScaleByTic(int time) {
        Size curr = size;
        for (Transformation t : scaleSchedule) {
            if (time >= t.getToTime()) {
                curr = (Size) t.getItem();
            } else if (time >= t.getFromTime()) {
                Size tmp = (Size) t.getItem();
                double percentage = (double) (time - t.getFromTime()) / (t.getToTime() - t.getFromTime());
                double one = curr.getFirstArg() + (tmp.getFirstArg() - curr.getFirstArg()) * percentage;
                double two = curr.getSecondArg() + (tmp.getSecondArg() - curr.getSecondArg()) * percentage;
                return new Size(one, two);
            } else {
                return curr;
            }
        }
        return curr;
    }

    @Override
    public ArrayList<LogNode> generateLog() {
        ArrayList<LogNode> log = new ArrayList<>();
        Position curr1 = position;
        for (Transformation t : moveSchedule) {
            Position tmp = (Position) t.getItem();
            String s = String.format("Shape %s moves from %s to" +
                    " %s from t=%d to t=%d", id, curr1, tmp, t.getFromTime(), t.getToTime());
            log.add(new LogNode(t.getFromTime(), s));
            curr1 = tmp;
        }

        colorSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
        Color curr2 = color;
        for (Transformation t : colorSchedule) {
            Color tmp = (Color) t.getItem();
            String s = String.format("Shape %s changes color from %s to" +
                    " %s from t=%d to t=%d", id, curr2, tmp, t.getFromTime(), t.getToTime());
            log.add(new LogNode(t.getFromTime(), s));
            curr2 = tmp;
        }

        scaleSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
        Size curr3 = size;
        for (Transformation t : scaleSchedule) {
            Size tmp = (Size) t.getItem();
            String s = String.format("Shape %s scales from %s: %.1f, %s: %.1f to" +
                    " %s: %.1f, %s: %.1f from t=%d to t=%d", id, sizeTitle[0], curr3.getFirstArg(),
                    sizeTitle[1], curr3.getSecondArg(), sizeTitle[0], tmp.getFirstArg(),
                    sizeTitle[1], tmp.getSecondArg(), t.getFromTime(), t.getToTime());
            log.add(new LogNode(t.getFromTime(), s));
            curr3 = tmp;
        }
        return log;
    }

    @Override
    public void move(Position pos, int fromTime, int toTime) {
        checkTime(fromTime, toTime);
        checkTimeOverLappedMove(fromTime, toTime);
        moveSchedule.add(new Transformation(fromTime, toTime, pos));
        moveSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
    }

    @Override
    public void changeColor(Color color, int fromTime, int toTime) {
        checkTime(fromTime, toTime);
        checkTimeOverLappedColor(fromTime, toTime);
        colorSchedule.add(new Transformation(fromTime, toTime, color));
        colorSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
    }

    @Override
    public void changeSize(Size size, int fromTime, int toTime) {
        checkTime(fromTime, toTime);
        checkTimeOverLappedScale(fromTime, toTime);
        scaleSchedule.add(new Transformation(fromTime, toTime, size));
        scaleSchedule.sort(Comparator.comparingInt(Transformation::getFromTime));
    }

    @Override
    public int getAppearTime() {
        return appearTime;
    }

    @Override
    public int getDisappearTime() {
        return disappearTime;
    }

    @Override
    public Image getAtTic(int time) {
        Position pos = getPosByTic(time);
        Color col = getColorByTic(time);
        Size size = getScaleByTic(time);
        return new Image(id, pos, col, shape, size);
    }

    /**
     * The sample format of the feature string is:
     *
     Shapes:
     Name: R
     Type: rectangle
     Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
     Appears at t=1
     Disappears at t=100

     Name: C
     Type: oval
     Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
     Appears at t=6
     Disappears at t=100
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: " + shape + "\n");
        sb.append(posTitle + ": " + position + ", ");
        sb.append(sizeTitle[0] + ": " + size.getFirstArg() + ", ");
        sb.append(sizeTitle[1] + ": " + size.getSecondArg() + ", ");
        sb.append("Color: " + color);
        sb.append(String.format("Appears at t=%d", appearTime));
        sb.append(String.format("Disappears at t=%d", disappearTime));
        return sb.toString();
    }
}
