public class Transformation {
    private int fromTime;
        private int toTime;
        private Action item;

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

