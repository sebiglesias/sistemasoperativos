package scheduler;

import processes.Process;
import processes.Resource;
import schedulingalgorithms.Run;
import schedulingalgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebi on 24/04/17.
 */
public class Scheduler {
    private List<Process> completed;
    private List<Process> ready;
    private List<Process> blocked;
    private List<Process> all;
    private SchedulingAlgorithm algorithm;
    private int clock;

//    public Scheduler(){
//        this.ready = new ArrayList<>();
//        this.blocked = new ArrayList<>();
//        this.events = new ArrayList<>();
//    }

    public Scheduler(SchedulingAlgorithm algorithm, List<Process> all) {
        this.blocked = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.all = all;
        this.algorithm = algorithm;

        Process minProcess = getMinTimeProcess();
        int minTime = minProcess.getArrivalTime();
        if (clock < minTime) {
            clock = minTime;
        }
        updateProcesses();
    }

    private void updateProcesses() {
        updateTimeOfBlockedProcess();
        all.forEach(process -> {
            if (process.nextIsIO()) {
                blocked.add(process);
            } else {
                algorithm.addProcess(process);
            }
        });
    }

    public void run() {
        while (completed.size() != all.size()) {
            Run next = algorithm.nextProcess();
            if (next != null) {
                next.getProcess().addInterval(clock, clock + next.getTime());
                clock += next.getTime();
                List<Process> noLongerBlocked = new ArrayList<>();
                blocked.forEach(process -> {
                    Resource resource = process.getResources().peek();
                    resource.consume(next.getTime());
                    if (resource.getRemainingTime()== 0) {
                        process.getResources().poll();
                        noLongerBlocked.add(process);
                        algorithm.addProcess(process);
                    }
                });
                blocked.removeAll(noLongerBlocked);
                Resource resource = next.getProcess().getResources().peek();
                if (resource != null) {
                    resource.consume(next.getTime());
                    if (resource.getRemainingTime() == 0) {
                        next.getProcess().getResources().poll();
                        try {
                            if (next.getProcess().nextIsIO()) {
                                blocked.add(next.getProcess());
                            } else {
                                algorithm.addProcess(next.getProcess());
                            }
                        } catch (NullPointerException e) {
                            completed.add(next.getProcess());
                        }
                    } else {
                        algorithm.addProcess(next.getProcess());
                    }
                } else {
                    completed.add(next.getProcess());
                }
                System.out.println(next.getProcess());
            } else {
                int timeBlocked = getMinTimeBlockedProcess();
                if (timeBlocked != 0) {
                    clock += timeBlocked;
                    List<Process> noMoreBlocked = new ArrayList<>();
                    blocked.forEach(process -> {
                        Resource resource = process.getResources().peek();//it better be IO
                        resource.consume(timeBlocked);
                        if (resource.getRemainingTime() == 0) {
                            process.getResources().poll();
                            noMoreBlocked.add(process);
                            algorithm.addProcess(process);
                        }
                    });
                    blocked.removeAll(noMoreBlocked);
                } else {
                    all.forEach(process -> {
                        if (process.hasLeft() && !completed.contains(process)) {
                            completed.add(process);
                        } else if (process.nextIsIO() && !blocked.contains(process)) {
                            blocked.add(process);
                        } else {
                            algorithm.addProcess(process);

                        }
                    });
                }
            }
        }
    }

    public List<Process> getProcesses() {
        return all;
    }

    private Process getMinTimeProcess() {
//        if (all.isEmpty())return new Process("empty");
        Process aux = all.get(0);
        for (Process process : all) {
            if (process.getArrivalTime() < aux.getArrivalTime()) {
                aux = process;
            }
        }
        return aux;
    }

    private int getMinTimeBlockedProcess() {
        try {
            int aux = blocked.get(0).getResources().peek().getRemainingTime();
            for (Process process : blocked) {
                if (process.getResources().peek().getRemainingTime() < aux) {
                    aux = process.getResources().peek().getRemainingTime();
                }
            }
            return aux;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    private void updateTimeOfBlockedProcess() {
        blocked.forEach(process -> {
            Resource resource = process.getResources().peek();
            resource.consume(clock);
            if (resource.getRemainingTime() >= clock) {
                process.getResources().remove(0);
                algorithm.addProcess(process);
            }
        });
    }



//    public List<Event> schedule(SchedulingAlgorithm alg, List<Process> processes){
//        int time = 0;
//
//        assignProccesses(processes);
//
//        while (!ready.isEmpty() || !blocked.isEmpty()){
//            if (!ready.isEmpty()){
//                alg.addProcess(ready);
//                if (alg.hasNextProcess()) {
//                    final Run run = alg.nextProcess();
//                    susbtractTimeFromBlocked(run.getTime(), blocked);
//                    if (run.getProcess().isLocked()) {
//                        ready.remove(run.getProcess());
//                        blocked.add(run.getProcess());
//                    }
//                    events.add(new Event(run.getProcess(),time,time+run.getTime()));
////                    consume(run);
//                    time += run.getTime();
//                }
//            }else{
//                susbtractTimeFromBlocked(1,blocked); //todo crear events de los procesos que se corrieron
//            }
//            passBlockedToReady();
//        }
//        return events;
//    }
//
//    private void passBlockedToReady() {
//        if (blocked.isEmpty())return;
//        this.blocked.forEach(p -> {
//            if (!p.isLocked()){
//                this.blocked.remove(p);
//                this.ready.add(p);
//            }
//        });
//    }
//
//    private void assignProccesses(List<Process> processes) {
//        processes.forEach(process -> {
//            if (process.isLocked())this.blocked.add(process);
//            else this.ready.add(process);
//        });
//    }
//
//    private void susbtractTimeFromBlocked(int time, List<Process> list){
//        list.forEach(p -> {
//            if (p.isLocked())p.consumeResource(time);
//        });
//    }
//
////    private void consume(Run run){
////        final Process process = run.getProcess();
////        ready.remove(process);
////        if (process.consumeResource(run.getTime())) {
////            if (process.isLocked()){
////                blocked.add(process);
////            }else{
////                ready.add(process);
////            }
////        }
////    }
//
//    public List<Event> events() {
//        return events;
//    }
}
