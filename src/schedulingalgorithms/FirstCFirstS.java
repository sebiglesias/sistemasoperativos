package schedulingalgorithms;

import processes.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sebi on 24/04/17.
 */
public class FirstCFirstS extends SchedulingAlgBase {

    private int standardTime;
    private Queue<Process> processQueue;


    FirstCFirstS(int time){
        this.standardTime = time;
        processQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void addProcess(List<Process> p) {
        processQueue.addAll(p);
    }

    @Override
    public void addProcess(Process p) {
        processQueue.add(p);
    }

    @Override
    public Run nextProcess() {
        if (hasNext()){
            Process p = processQueue.poll();

        }
    }

    @Override
    public boolean hasNextProcess() {
        return hasNext();
    }

    private boolean hasNext(){
        return processQueue.peek()!=null;
    }
}
