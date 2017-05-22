package schedulingalgorithms;

import processes.Process;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by sebi on 24/04/17.
 */
public class RoundRobin extends SchedulingAlgBase {

    private Queue<Process> ready;
    private int quantum;

    public RoundRobin() {
        this.ready = new LinkedList<>();
        this.quantum = 5;//segun fer
    }

    public void addProcess(Process process) {
        ready.add(process);
    }

    @Override
    public Run nextProcess() {
        Process process = ready.poll();
        if (process != null) {
            return new Run(process, quantum);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Round Robin";
    }
}
