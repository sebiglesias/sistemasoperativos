package schedulingalgorithms;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public interface SchedulingAlgorithm {
    void addProcess(Process p);
    Run nextProcess();

}
