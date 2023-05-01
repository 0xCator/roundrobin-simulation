public class Main {
    public static void main(String[] args) {
        RoundRobin sim = new RoundRobin(2);
        sim.addProcess(new process(1, 7, 0));
        sim.addProcess(new process(2, 4, 1));
        sim.addProcess(new process(3, 15, 2));
        sim.addProcess(new process(4, 11, 3));
        sim.addProcess(new process(5, 20, 4));
        sim.addProcess(new process(6, 9, 4));
        sim.schedule();
        System.out.println("Scheduling done!");
        for (process p : sim.processes) {
            System.out.println(p.getID() + " " + p.getArrivalTime() + " " + p.getBurstTime() + " " + p.getCompleteTime()
                                + " " + p.getTurnAroundTime() + " " + p.getWaitingTime());
        }
        System.out.println("Avg comp: " + sim.getAvgCompleteTime());
        System.out.println("Avg turn: " + sim.getAvgTurnAroundTime());
        System.out.println("Avg wait: " + sim.getAvgWaitingTime());
    }
}