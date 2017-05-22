package processes;

/**
 * Created by sebi on 15/05/17.
 */
public class IoResource implements Resource{

    private int time;

    public IoResource(int time) {
        this.time = time;
    }

    @Override
    public boolean isIO() {
        return true;
    }

    @Override
    public boolean isCPU() {
        return false;
    }

    @Override
    public int getRemainingTime() {
        if (time<0)return 0;
        return time;
    }

    @Override
    public void consume(int time) {
        this.time -= time;
    }

    public String toString(){
        return "IO: " + this.time;
    }
}
