
public class process {

    private int ID;
    private int ArrivalTime;
    private int BurstTime;
    private int StartingBurstTime;
    private int CompleteTime;
    private int ResponseTime;
    private int TurnAroundTime;
    private int WaitingTime;



    process(int ID, int BurstTime, int ArrivalTime){
        this.ID = ID;
        this.BurstTime = BurstTime;
        this.StartingBurstTime = BurstTime;
        this.ArrivalTime = ArrivalTime;
        this.ResponseTime = -1;
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

    public void setBurstTime(int BurstTime)
    {
        if (BurstTime < 0)
            this.BurstTime = 0;
        else
            this.BurstTime = BurstTime;
    }

    public void setArrivalTime(int ArrivalTime){
        this.ArrivalTime = ArrivalTime;
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

    public int getResponseTime() {
        return ResponseTime;
    }

    public void setResponseTime(int responseTime) {
        ResponseTime = responseTime;
    }
}
