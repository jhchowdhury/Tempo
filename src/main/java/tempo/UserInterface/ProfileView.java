package tempo.UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tempo.ProfileManagement.Profile;
import tempo.ProfileManagement.UserProfileController;

public class ProfileView {
    @FXML
    private TextField txtProfileName;

    @FXML
    private TextField txtProfileSurname;

    @FXML
    private TextField txtProfileUsername;

    @FXML
    private TextField txtProfileEmail;

    @FXML
    private PasswordField txtProfilePassword;

    @FXML
    private PasswordField txtProfileRePassword;

    @FXML
    private Label lblProfileChanges;

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
    private void changeName (ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
        System.out.println(txtProfileName.getText());
    }

    @FXML
    private void changeSurname(ActionEvent event) throws Exception {
        System.out.println(txtProfileSurname.getText());
    }

    @FXML
    private void changeUsername(ActionEvent event) throws Exception {
        System.out.println(txtProfileUsername.getText());
    }

    @FXML
    private void changeEmail (ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
        System.out.println(txtProfileEmail.getText());
    }

    @FXML
    private void changePassword(ActionEvent event) throws Exception {
        if(txtProfilePassword.getText().equals(txtProfileRePassword.getText())){
            UserProfileController control = new UserProfileController();
            System.out.println(txtProfilePassword.getText());
            lblProfileChanges.setText("Password is changed!");
        }
        else{
            lblProfileChanges.setText("Rewrite passwords!");
        }
    }

    @FXML
    private void changeProfilePicture(ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
        System.out.println("clicked");
    }

    @FXML
    private void cmbNameVisibility(ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

    @FXML
    private void cmbEmailVisibility(ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

    @FXML
    private void cmbPersonalSet(ActionEvent event) throws Exception {
        UserProfileController control = new UserProfileController();
    }

}
