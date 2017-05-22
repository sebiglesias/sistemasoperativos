package processes;

import events.State;

import java.util.Queue;

/**
 * Created by sebi on 24/04/17.
 */
public class Process {

    private String name;
    private int priority;
    private int arrivalTime;
    private Queue<Resource> resources;

    public Process(String name, int priority, int arrivalTime, Queue<Resource> resources) {
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.resources = resources;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public Queue<Resource> getResources() {
        return resources;
    }

    public boolean hasNextResource(){
        return !resources.isEmpty();
    }

    public boolean isLocked(){
        return resources.peek().isIO();
    }

    public boolean consumeResource(int time){
        if(resources.isEmpty())return false;
        if (resources.peek().getRemainingTime()-time <=0){
            final int remainingTime = resources.poll().getRemainingTime();
            return consumeResource(time - remainingTime);
        }
        resources.peek().consume(resources.peek().getRemainingTime());
        return true;
    }

    public State state() {
        return ;
    }
}
