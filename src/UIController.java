import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.cell.*;
import javafx.collections.*;
import javafx.util.converter.*;
import java.util.*;

public class UIController {
    //Operation class reference
    private RoundRobin roundRobinOperation;
    //Components
    @FXML
    private Label errorMsgLabel;
    @FXML
    private TextField processCountField;
    @FXML
    private TextField quantumCountField;
    @FXML
    private Button runButton;
    @FXML
    private TableView<ProcessView> processTable;
    @FXML
    private TableColumn<ProcessView, String> processColumn;
    @FXML
    private TableColumn<ProcessView, Integer> arrivalColumn;
    @FXML
    private TableColumn<ProcessView, Integer> burstColumn;
    @FXML
    private Canvas resCanvas;

    ObservableList<ProcessView> processList = FXCollections.observableArrayList();

    public void setRoundRobinOperation(RoundRobin roundRobin) {
        this.roundRobinOperation = roundRobin;
    }

    private boolean isValidInput(String input) {
        if (input.matches("^[0-9]*$") && !input.isBlank())
            return Integer.parseInt(input) > 0;
        return false;
    }

    private boolean isValidInput(int input, boolean greaterThanZero) {
        return (greaterThanZero) ? input>0 : input>=0;

    }

    @FXML
    public void initialize() {
        //Connecting the columns to the properties from the view class
        processColumn.setCellValueFactory(new PropertyValueFactory<>("pID"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        arrivalColumn.setOnEditCommit((CellEditEvent<ProcessView, Integer> t) -> {
            ((ProcessView) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setArrivalTime(t.getNewValue());
        });
        burstColumn.setCellValueFactory(new PropertyValueFactory<>("burstTime"));
        burstColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        burstColumn.setOnEditCommit((CellEditEvent<ProcessView, Integer> t) -> {
            ((ProcessView) t.getTableView()
                    .getItems()
                    .get(t.getTablePosition().getRow()))
                    .setBurstTime(t.getNewValue());
        });

        //Using the processList arrayList with the table as a data source
        processTable.setItems(processList);
    }

    @FXML
    private void onCreateClick() {
        //Getting input from fields
        String processInput = processCountField.getText();
        String quantumInput = quantumCountField.getText();
        //Checking if input is valid
        boolean validCheck = isValidInput(processInput) && isValidInput(quantumInput);
        if (validCheck) {
            //Clearing the error message
            errorMsgLabel.setText("");
            //Enabling the run button
            runButton.setDisable(false);
            //Showing the table, emptying the list of processes and repopulating it based on the given number
            processTable.setVisible(true);
            processTable.getItems().clear();
            processList.removeAll();
            for (int i = 0; i < Integer.parseInt(processInput); i++)
                processList.add(new ProcessView("P" + i, 0, 0));
            //Setting the operation class' quantum time
            roundRobinOperation.setTimeQuantum(Integer.parseInt(quantumInput));
        }
        else errorMsgLabel.setText("Error: Enter appropriate values");
    }

    @FXML
    private void onRunClick() {
        GraphicsContext gc = resCanvas.getGraphicsContext2D();
        //Step 1: Validate all input
        for (ProcessView proc:processList) {
            boolean check = isValidInput(proc.getArrivalTime(), false) &&
                    isValidInput(proc.getBurstTime(), true);
            if (!check) {
                errorMsgLabel.setText("Error: Enter appropriate values (In the process table)");
                return;
            } else
                errorMsgLabel.setText("");
        }
        //Step 2: Parse all input into objects and add to RoundRobin object
        for (int i = 0; i < processList.size(); i++) {
            process proc = new process(i, processList.get(i).getBurstTime(), processList.get(i).getArrivalTime());
            roundRobinOperation.addProcess(proc);
        }
        //Step 3: Clear the canvas and run the operation
        gc.clearRect(0,0,640,360);
        roundRobinOperation.schedule();
        //Step 4: Print Gantt chart
        drawGantt(gc);
        //Step 5: Print average values
        double compTime = roundRobinOperation.getAvgCompleteTime();
        double waitingTime = roundRobinOperation.getAvgWaitingTime();
        double turnAround = roundRobinOperation.getAvgTurnAroundTime();
        String avgText = "Average complete time: " + compTime + "\tAverage waiting time: " +
                waitingTime + "\tAverage turnaround time: " + turnAround;
        gc.strokeText(avgText, 0, 56);
        //Step 6: Print each process' values
        ArrayList<process> procList = roundRobinOperation.getProcesses();

        //Step 7: Clear list to avoid future conflicts
        roundRobinOperation.clearProcesses();
    }

    private void drawGantt(GraphicsContext graphicsContext) {
        int ganttLength = 3;
        double boxLength = 30;
        graphicsContext.strokeText("0", 0, 44);
        for (int i = 0; i < ganttLength; i++) {
            double boxStart = 2 + (boxLength * i), boxEnd = boxLength * (i+1), nameSpace;
            graphicsContext.strokeRect(boxStart, 2, boxLength, 30); //Process box
            graphicsContext.strokeText(i+2 + "", boxEnd, 44); //Process end point
            nameSpace = (i>9) ? 5 : 8; //The name's centering depends on the PID
            graphicsContext.strokeText("P"+i, boxStart + nameSpace, 20); //Process name
        }
    }
}