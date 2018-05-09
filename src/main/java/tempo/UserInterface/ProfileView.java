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
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;
import tempo.ProfileManagement.Profile;
import tempo.ProfileManagement.UserProfileController;

import java.util.ArrayList;

public class ProfileView {
    //text field's name
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

    //method for logging out

    @FXML
    private void logOutFromDatabase(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //method for going back to calendar

    @FXML
    private void goBackToCalendar(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //method for changing name
    //

    @FXML
    private void changeName (ActionEvent event) throws Exception {
        //System.out.println(txtProfileName.getText());
        //ArrayList<Profile> = DatabaseInteraction.getInstance().getDataListFromDatabase("profile","name", Storage.getInstance(), Profile.class);
        Storage.getInstance().getUser().name = txtProfileName.getText();
        CommunicationHelper.getInstance().updateProfile(Storage.getInstance().user);

    }

    //method for changing surname

    @FXML
    private void changeSurname(ActionEvent event) throws Exception {
        //System.out.println(txtProfileSurname.getText());
        Storage.getInstance().getUser().surname = txtProfileSurname.getText();
        CommunicationHelper.getInstance().updateProfile(Storage.getInstance().user);

    }

    //method for changing username

    @FXML
    private void changeUsername(ActionEvent event) throws Exception {
        //System.out.println(txtProfileUsername.getText());
    }

    //method for changing email

    @FXML
    private void changeEmail (ActionEvent event) throws Exception {
        //System.out.println(txtProfileEmail.getText());
        Storage.getInstance().getUser().email = txtProfileEmail.getText();
        CommunicationHelper.getInstance().updateProfile(Storage.getInstance().user);
    }

    //method for changing password

    @FXML
    private void changePassword(ActionEvent event) throws Exception {

    }

    //method for changing profile picture

    @FXML
    private void changeProfilePicture(ActionEvent event) throws Exception {
        //??????
    }

    //method for changing visibility of name

    @FXML
    private void cmbNameVisibility(ActionEvent event) throws Exception {
       //Storage.getInstance().getUser().preferences
    }

    //method for changing visibility of email


    @FXML
    private void cmbEmailVisibility(ActionEvent event) throws Exception {

    }

    //method for changing visibility of personal set


    @FXML
    private void cmbPersonalSet(ActionEvent event) throws Exception {

    }

}
