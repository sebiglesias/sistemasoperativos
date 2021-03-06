package processes;

/**
 * Created by sebi on 15/05/17.
 */
public class CpuResource implements Resource {

    private int time;

    public CpuResource(int time){
        this.time = time;
    }

    @Override
    public boolean isIO() {
        return false;
    }

    @Override
    public boolean isCPU() {
        return true;
    }

    @Override
    public int getRemainingTime() {
        if(time<0)return 0;
        return time;
    }

    @Override
    public void consume(int time) {
        this.time -= time;
        if (this.time<0)this.time=0;
    }

    public String toString(){
        return "CPU: " + this.time;
    }

}
