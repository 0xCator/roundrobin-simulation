
public class process {

    private int ID;
    private int ArrivalTime;
    private int BurstTime;
    private int StartingBurstTime;
    private int CompleteTime;
    private int TurnAroundTime;
    private int WaitingTime;



    process(){
        StartingBurstTime = BurstTime;
    }


    public int getID(){
        return ID;
    }

    public int getArrivalTime(){
        return ArrivalTime;
    }

    public int getBurstTime(){
        return BurstTime;
    }

    public int getStartingBurstTime(){
        return StartingBurstTime;
    }

    public int getCompleteTime(){
        return CompleteTime;
    }

    public int getTurnAroundTime(){
        return TurnAroundTime;
    }

    public int getWaitingTime(){
        return WaitingTime;
    }

    public void setID(int ID){
        this.ID  = ID;
    }
    
    public void setArrivalTime(int ArrivalTime){
        this.ArrivalTime = ArrivalTime;
    }
    public void setBurstTime(int BurstTime){
        this.BurstTime = BurstTime;
    }
    public void setCompleteTime(int CompleteTime){
        this.CompleteTime = CompleteTime;
    }
    public void setTurnAroundTime(int TurnAroundTime){
        this.TurnAroundTime = TurnAroundTime;
    }
    public void setWaitingTime(int WaitingTime){
        this.WaitingTime = WaitingTime;
    }
}
