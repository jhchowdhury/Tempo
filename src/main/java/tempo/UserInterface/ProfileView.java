package tempo.UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tempo.ProfileManagement.Profile;
import tempo.ProfileManagement.UserProfileController;

public class ProfileView {
    @FXML
    private  TextField txtProfileUsername;

    @FXML
    private void changeUsername(ActionEvent event) throws Exception {
        System.out.println(txtProfileUsername.getText());
    }

    @FXML
    private void logOutFromDatabase(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goBackToCalendar(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void changeUserame (ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

    @FXML
    private void changeEmail (ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

    @FXML
    private void changeName (ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }


    @FXML
    private void changeSurname(ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

}
