package events;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public class Event {

    private Process process;
    private int startTime;
    private int endTime;
    private State state;

    public Event(Process process, int startTime, int endTime) {
        this.process = process;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = process.state();
    }
}
