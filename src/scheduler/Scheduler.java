package scheduler;

import events.Event;
import processes.Process;
import schedulingalgorithms.Run;
import schedulingalgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebi on 24/04/17.
 */
public class Scheduler {

    private List<Process> ready;
    private List<Process> blocked;
    private List<Event> events;

    Scheduler(){
        this.ready = new ArrayList<>();
        this.blocked = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public List<Event> schedule(SchedulingAlgorithm alg, List<Process> processes){
        int time = 0;

        assignProccesses(processes);

        while (!ready.isEmpty() || !blocked.isEmpty()){
            if (!ready.isEmpty()){
                alg.addProcess(ready);
                if (alg.hasNextProcess()) {
                    final Run run = alg.nextProcess();
                    time += run.getTime();
                    consume(run);
                    susbtractTimeFromBlocked(run.getTime(), blocked);

                }
            }else{
                susbtractTimeFromBlocked(1,blocked); //todo crear events de los procesos que se corrieron
            }
            passBlockedToReady();
        }
        return events;
    }

    private void passBlockedToReady() {
        this.blocked.forEach(p -> {
            if (!p.isLocked()){
                this.blocked.remove(p);
                this.ready.add(p);
            }
        });
    }

    private void assignProccesses(List<Process> processes) {
        processes.forEach(process -> {
            if (process.isLocked())this.blocked.add(process);
            else this.ready.add(process);
        });
    }

    private void susbtractTimeFromBlocked(int time, List<Process> list){
        list.forEach(p -> {
            if (p.isLocked())p.consumeResource(time);
        });
    }

    private void consume(Run run){
        final Process process = run.getProcess();
        ready.remove(process);
        if (process.consumeResource(run.getTime())) {
            if (process.isLocked()){
                blocked.add(process);
            }else{
                ready.add(process);
            }
        }
    }

}
