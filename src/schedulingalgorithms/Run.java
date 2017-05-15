package schedulingalgorithms;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public class Run {
    private Process p;
    int time;

    public Run(Process p, int time) {
        this.p = p;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public Process getProcess() {
        return p;
    }
}
