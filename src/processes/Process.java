package processes;

import java.util.*;

/**
 * Created by sebi on 24/04/17.
 */
public class Process {

    private int id;
    private String name;
    private int priority;
    private int arrivalTime;
    private Queue<Resource> resources;
    private List<Interval> intervals;

    public Process(int id, String name) {
        this.id = id;
        this.name = name;
        resources =  new LinkedList<>();
        intervals = new ArrayList<>();
    }


    public Queue<Resource> getResources() {
        return resources;
    }

    public boolean nextIsIO() {
        return resources.peek() instanceof IoResource;
    }

    public boolean hasLeft() {
        return resources.size() == 0;
    }

    public boolean nextIsCPU() {
        return resources.peek() instanceof CpuResource;
    }

    public Process priority(int priority) {
        this.priority = priority;
        return this;
    }

    public Process arrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public Process resources(Resource... resources) {
        this.resources.addAll(Arrays.asList(resources));
        return this;
    }

    public void addInterval(int start, int end) {
        intervals.add(Interval.interval(start, end));
    }

    @Override
    public String toString() {
        return "Process{" +
                ", priority=" + priority +
                ", arrivalTime=" + arrivalTime +
                ", resources=" + resources +
                ", intervals=" + intervals +
                '}';
    }


    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getName(){
        return name;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public int getId() {
        return id;
    }

//    public void priority(int priority) {
//        this.priority = priority;
//    }
//
//    public void arrivalTime(int arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public int getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void resources(Resource... resources){
//        this.queue.addAll(Arrays.asList(resources));
//    }
//
//    public Queue<Resource> getQueue() {
//        return queue;
//    }
//
//    public boolean hasNextResource(){
//        return !queue.isEmpty();
//    }
//
//    public boolean isLocked(){
//        if (queue.isEmpty())return false;
//        return queue.peek().isIO();
//    }
//
//    public boolean consumeResource(int time){
//        if(queue.isEmpty() || time ==0)return false;
//
//        if (queue.peek().getRemainingTime()-time <0){
//            final int remainingTime = queue.poll().getRemainingTime();
//            return consumeResource(time - remainingTime);
//        }
//        if (queue.peek().getRemainingTime() == 0){
//            queue.poll();
//            return true;
//        }
//
//        queue.peek().consume(time);
//        return true;
//    }
}
