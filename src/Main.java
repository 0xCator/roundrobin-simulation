import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        RoundRobin sim = new RoundRobin(5);
        sim.addProcess(new process(0, 7, 0));
        sim.addProcess(new process(1, 4, 1));
        sim.addProcess(new process(2, 15, 2));
        sim.addProcess(new process(3, 11, 3));
        sim.addProcess(new process(4, 20, 4));
        sim.addProcess(new process(5, 9, 4));
        sim.schedule();
        System.out.println("Scheduling done!");
        for (process p : sim.getProcesses()) {
            System.out.println(p.getID() + " " + p.getArrivalTime() + " " + p.getBurstTime() + " " + p.getCompleteTime()
                                + " " + p.getTurnAroundTime() + " " + p.getWaitingTime());
        }
        System.out.println("Avg comp: " + sim.getAvgResponseTime());
        System.out.println("Avg turn: " + sim.getAvgTurnAroundTime());
        System.out.println("Avg wait: " + sim.getAvgWaitingTime());
        for (ArrayList List: sim.getGanttChart())
        {
            System.out.println(List);
        }
    }
}
