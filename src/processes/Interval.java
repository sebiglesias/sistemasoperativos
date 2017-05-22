package processes;

/**
 * Created by sebi on 22/05/17.
 */
public class Interval {

    int start;
    int end;

    private Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public static Interval interval(int start, int end) {
        return new Interval(start, end);
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

