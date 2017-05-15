package schedulingalgorithms;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public class SchedulingAlgBase implements SchedulingAlgorithm {
    @Override
    public void addProcess(Process p) {

    }

    @Override
    public Run nextProcess() {
        return null;
    }

    @Override
    public boolean hasNextProcess() {
        return false;
    }
}
