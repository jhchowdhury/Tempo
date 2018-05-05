package tempo.UserInterface;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tempo.DataManagement.CommunicationHelper;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        Scene login = new Scene(root);
        primaryStage.setTitle("Tempo App");
        primaryStage.setScene(login);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

