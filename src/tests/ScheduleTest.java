package tests;

import processes.CpuResource;
import processes.Interval;
import processes.IoResource;
import processes.Process;
import scheduler.Scheduler;
import schedulingalgorithms.FirstCFirstS;
import schedulingalgorithms.RoundRobin;
import schedulingalgorithms.SchedulingAlgorithm;
import schedulingalgorithms.ShortestFirst;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by sebi on 22/05/17.
 */
public class ScheduleTest {
    public static void main(String[] args) throws IOException {

        FirstCFirstS FCFSalg = new FirstCFirstS(0);

        Scheduler scheduler = new Scheduler(FCFSalg, getProcessList());

        scheduler.run();

        scheduler.getProcesses().stream().map(Process::getIntervals).forEach(x->x.forEach(System.out::println));

        generateImage(FCFSalg,scheduler);


        RoundRobin roundRobin = new RoundRobin();

        scheduler = new Scheduler(roundRobin, getProcessList());

        scheduler.run();

        generateImage(roundRobin,scheduler);

        ShortestFirst shortestFirst = new ShortestFirst();

        scheduler = new Scheduler(shortestFirst, getProcessList());

        scheduler.run();

        generateImage(shortestFirst,scheduler);

    }


    public static void generateImage(SchedulingAlgorithm algorithm, Scheduler scheduler) throws IOException {
        BufferedImage bImg = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
//        cg.fillRect(0,0,640,480);
//        cg.setBackground(Color.white);
        cg.drawString(algorithm.toString(), bImg.getWidth() / 2 - 50, 20);
        List<Process> processes = scheduler.getProcesses();
//        int count =1;
        processes.forEach(p -> {
            int y2 = 30 + p.getId() * 30;
//            increase(count);
            cg.drawString("Process: " +p.getName(), 20, y2);
            List<Interval> intervals = p.getIntervals();
            for (Interval interval : intervals) {
                cg.drawLine(100 + interval.getStart()* 2, y2, 100 + interval.getEnd()* 2, y2);
                Stroke currentStroke = cg.getStroke();

                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0);
                cg.setStroke(dashed);
                cg.drawLine(100 + interval.getStart()* 2, 30, 100 + interval.getStart() * 2, 400);
                cg.drawLine(100 + interval.getEnd()* 2, 30, 100 + interval.getEnd()* 2, 400);

                cg.setStroke(currentStroke);
            }
        });
//        cg.drawLine(0, 0, 640, 480);
        ImageIO.write(bImg, "png", new File("./" + algorithm.toString() + ".png"));
    }

    private static List<Process> getProcessList(){
        Process process = new Process(1,"p1");
        process.priority(4);
        process.arrivalTime(6);
        process.resources(new CpuResource(60), new IoResource(38), new CpuResource(30));

        Process process1 = new Process(2,"p2");
        process1.priority(3);
        process1.arrivalTime(8);
        process1.resources(new CpuResource(50), new IoResource(20), new CpuResource(70));


        Process process2 = new Process(3,"p3");
        process2.priority(2);
        process2.arrivalTime(7);
        process2.resources(new CpuResource(10), new IoResource(60), new CpuResource(20));

        return asList(process,process1,process2);
    }

}
