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
        FXMLLoader loader = new FXMLLoader();
        GridPane basePane = loader.load(getClass().getResource("gui.fxml"));

        Scene scene = new Scene(basePane);
        stage.setTitle("Amazing round robin simulation");
        stage.setScene(scene);
        stage.show();
    }
}