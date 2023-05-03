import javafx.beans.property.*;

public class ProcessView {
    SimpleStringProperty pID;
    SimpleIntegerProperty arrivalTime;
    SimpleIntegerProperty burstTime;

    ProcessView(String pID, int arrivalTime, int burstTime) {
        this.pID = new SimpleStringProperty(pID);
        this.arrivalTime = new SimpleIntegerProperty(arrivalTime);
        this.burstTime = new SimpleIntegerProperty(burstTime);
    }

    public String getPID() {
        return pID.getValue();
    }

    public void setPID(String pID) {
        this.pID = new SimpleStringProperty(pID);
    }

    public int getArrivalTime() {
        return arrivalTime.getValue();
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = new SimpleIntegerProperty(arrivalTime);
    }

    public int getBurstTime() {
        return burstTime.getValue();
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = new SimpleIntegerProperty(burstTime);
    }
}
