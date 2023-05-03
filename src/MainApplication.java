import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        RoundRobin roundRobin = new RoundRobin(0);
        FXMLLoader loader = new FXMLLoader();
        GridPane basePane = loader.load(getClass().getResource("gui.fxml").openStream());
        UIController uiController = (UIController)loader.getController();
        uiController.setRoundRobinOperation(roundRobin);
        Scene scene = new Scene(basePane);
        stage.setTitle("Round Robin Simulation");
        stage.setScene(scene);
        stage.show();
    }
}