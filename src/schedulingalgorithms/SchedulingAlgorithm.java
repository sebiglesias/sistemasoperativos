package schedulingalgorithms;

import processes.Process;

import java.util.List;

/**
 * Created by sebi on 24/04/17.
 */
public interface SchedulingAlgorithm {

    void addProcess(List<Process> p);
    Run nextProcess();
    boolean hasNextProcess();

}
