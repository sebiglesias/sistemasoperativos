package processes;

/**
 * Created by sebi on 24/04/17.
 */
public interface Resource {

    public boolean isIO();

    public boolean isCPU();

    public int getRemainingTime();

    public void consume(int time);

}
