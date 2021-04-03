/**
 * This represents a transformation Node which includes an action and a time period in it.
 */
public class Transformation {
    private int fromTime;
        private int toTime;
        private Action item;

    /**
     * Initialize the time period and action.
     * @param fromTime starting time of time transformation
     * @param toTime ending time of time transformation
     * @param item action this transformation does
     */
        public Transformation(int fromTime, int toTime, Action item) {
            this.fromTime = fromTime;
            this.toTime = toTime;
            this.item = item;
        }

        public int getFromTime() {
            return fromTime;
        }

        public int getToTime() {
            return toTime;
        }

        public Action getItem() {
            return item;
        }
    }

