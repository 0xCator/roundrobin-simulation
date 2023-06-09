import java.util.*;

public class RoundRobin {
    private int QuantumTime;
    private ArrayList<process> processes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> ganttChart = new ArrayList<>();
    private double avgCompleteTime, avgTurnAroundTime, avgWaitingTime, avgResponseTime;

    public RoundRobin(int Q) {
        this.QuantumTime = Q;
        this.avgCompleteTime = 0;
        this.avgTurnAroundTime = 0;
        this.avgWaitingTime = 0;
    }

    public void setTimeQuantum(int Q) {
        this.QuantumTime = Q;
    }

    public void addProcess(process p) {
        processes.add(p);
    }

    public void clearProcesses() {
        processes.clear();
        ganttChart.clear();
    }

    public void schedule() {
        int c = 0, alarm = 0, chartBlock = 0;
        int finishedProcesses = 0;
        boolean CPUState = false;
        process CPUProcess = new process(0,0,0);
        Queue<process> processQueue = new LinkedList<>();
        while (finishedProcesses != processes.size()) {
            //Step 1: Enqueue for initial arrival time
            for (process p : processes) {
                if (p.getArrivalTime() == c)
                    processQueue.add(p);
            }
            //Step 2: Alarm strike
            if (c == alarm && c != 0) {
                CPUState = false;
                //If the process is not finished, requeue
                if (CPUProcess.getBurstTime() != 0)
                    processQueue.add(CPUProcess);
                else {
                    //Otherwise, calculate all values and don't requeue
                    CPUProcess.setCompleteTime(c);
                    calc(CPUProcess);
                    finishedProcesses++;
                }
                //Appending to the gantt chart arraylist
                ganttChart.add(new ArrayList<Integer>());
                ganttChart.get(chartBlock).add(CPUProcess.getID());
                ganttChart.get(chartBlock).add(c);
                chartBlock++;
            }
            //Step 3: Add to CPU if no processes are being used
            if (!CPUState && !processQueue.isEmpty()) {
                CPUProcess = processQueue.remove(); //Dequeue and hold the object
                //Set the process response time
                if (CPUProcess.getResponseTime() == -1) {
                    CPUProcess.setResponseTime(Math.abs(c - CPUProcess.getArrivalTime()));
                }
                //Pre-set the process' burst time
                if (CPUProcess.getBurstTime() >= QuantumTime) {
                    alarm = c + QuantumTime;
                    CPUProcess.setBurstTime(CPUProcess.getBurstTime() - QuantumTime);
                } else {
                    alarm = c + CPUProcess.getBurstTime();
                    CPUProcess.setBurstTime(0);
                }
                CPUState = true;
            }
            c++; //Increment the clock
        }
        calcAverage();
    }
    private void calc(process p) {
        //Calculate turnaround time
        p.setTurnAroundTime(p.getCompleteTime() - p.getArrivalTime());

        //Calculate waiting time
        p.setWaitingTime(p.getTurnAroundTime() - p.getStartingBurstTime());
    }

    private void calcAverage() {
        //Resetting the average values
        this.avgCompleteTime = 0;
        this.avgTurnAroundTime = 0;
        this.avgWaitingTime = 0;
        this.avgResponseTime = 0;
        for (process p : processes) {
            avgWaitingTime += p.getWaitingTime();
            avgCompleteTime += p.getCompleteTime();
            avgTurnAroundTime += p.getTurnAroundTime();
            avgResponseTime += p.getResponseTime();
        }
        avgTurnAroundTime = (int)(avgTurnAroundTime * 100 / processes.size()) / 100.0;
        avgWaitingTime = (int)(avgWaitingTime * 100 / processes.size()) / 100.0;
        avgCompleteTime = (int)(avgCompleteTime * 100 / processes.size()) / 100.0;
        avgResponseTime = (int)(avgResponseTime * 100 / processes.size()) / 100.0;
    }

    public double getAvgResponseTime() {
        return avgResponseTime;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public double getAvgTurnAroundTime() {
        return avgTurnAroundTime;
    }

    public ArrayList<process> getProcesses() {
        return processes;
    }

    public ArrayList<ArrayList<Integer>> getGanttChart() {
        return ganttChart;
    }
}

