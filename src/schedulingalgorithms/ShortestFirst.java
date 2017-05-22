package schedulingalgorithms;

import processes.Process;
import processes.Resource;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sebi on 24/04/17.
 */
public class ShortestFirst implements SchedulingAlgorithm {

    private Queue<Process> ready;

    public ShortestFirst() {
        this.ready = new LinkedBlockingQueue<>();
    }

    @Override
    public void addProcess(Process process) {
        ready.add(process);
    }

    @Override
    public Run nextProcess() {
        Optional<Process> process = getShortestProcess();
        if (process.isPresent()) {
            Resource resource = process.get().getResources().peek();
            if(resource != null){
                return new Run(process.get(), resource.getRemainingTime());
            }

        }

        return null;
    }

    private Optional<Process> getShortestProcess() {
        Process process;
        if (ready.size() >= 2) {
            return this.ready.stream().reduce((p1, p2) -> {
                Resource resource1 = p1.getResources().peek();
                Resource resource2 = p2.getResources().peek();
                if (resource1 == null && resource2 !=null)return p2;
                if (resource2 != null) {
                    return resource1.getRemainingTime() < resource2.getRemainingTime() ? p1 : p2;
                }
                return p1;
            });
        }
        return Optional.of(ready.poll());
    }

    @Override
    public String toString() {
        return "ShortestFirst";
    }

}
