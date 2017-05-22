package schedulingalgorithms;

import processes.CpuResource;
import processes.Process;
import processes.Resource;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sebi on 24/04/17.
 */
public class FirstCFirstS extends SchedulingAlgBase {

    private int standardTime;
    private Queue<Process> processQueue;


    public FirstCFirstS(int time){
        this.standardTime = time;
        processQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void addProcess(Process p) {
        processQueue.add(p);
    }

    @Override
    public Run nextProcess() {
        Process process = processQueue.poll();
        if (process != null) {
            Resource resource = process.getResources().peek();
            if (resource instanceof CpuResource){
                return new Run(process, resource.getRemainingTime());
            }
            return null;
        }
        return null;
    }

    private boolean hasNext(){
        return processQueue.peek()!=null;
    }

    public String toString(){
        return "First Come - First Served";
    }
}
