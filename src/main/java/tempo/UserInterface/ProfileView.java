package tempo.UserInterface;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.Storage;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ProfileView implements Initializable {
    //text field's name
    @FXML
    private JFXTextField txtProfileName;

    @FXML
    private JFXTextField txtProfileSurname;

    @FXML
    private JFXTextField txtProfileEmail;

    @FXML
    private JFXTextField txtInfoName;

    @FXML
    private JFXTextField txtInfoSurname;

    @FXML
    private JFXTextField txtInfoUsername;

    @FXML
    private JFXTextField txtInfoEmail;

    @FXML
    private Label lblBigName;

    @FXML
    private javafx.scene.image.ImageView profPic;



    @FXML
    private Label lblProfileChanges;

    private Stage stage;
    /*
    public ProfileView() {
        txtInfoName.setText("Name");
        txtInfoSurname.setText("Surname");
        txtInfoUsername.setText("Username");
        txtInfoEmail.setText("example123@mailmail.com");
    }
    */

    @FXML
    private void logOutFromDatabase(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interfaces/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //method for going back to calendar

    @FXML
    private void goBackToCalendar(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interfaces/MainView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //method for changing name
    //

    @FXML
    private void changeName (ActionEvent event) throws Exception {
        Storage.getInstance().getUser().name = txtProfileName.getText();
        //to display
        txtInfoName.setText(txtProfileName.getText());
        //update
        CommunicationHelper.getInstance().updateProfile(Storage.getInstance().user);
    }

    //method for changing surname

    @FXML
    private void changeSurname(ActionEvent event) throws Exception {
        //System.out.println(txtProfileSurname.getText());
        System.out.println("triggered!");
        System.out.println( txtProfileSurname.getText());

        Storage.getInstance().getUser().surname = txtProfileSurname.getText();
        //to display
        txtInfoSurname.setText(txtProfileSurname.getText());
        //update
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
        //to display
        txtInfoEmail.setText(txtProfileEmail.getText());
        //update
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

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        txtInfoName.setText(Storage.getInstance().getUser().name);
        txtInfoSurname.setText(Storage.getInstance().getUser().surname);
        txtInfoEmail.setText(Storage.getInstance().getUser().email);
        txtInfoUsername.setText(Storage.getInstance().getUsr().username);
        lblBigName.setText(Storage.getInstance().getUsr().username);




    }

    @FXML
    private void changePP (ActionEvent event) throws Exception {

        try {
            //file chooser
            final FileChooser fileChooser = new FileChooser();

            //get URL
            File file;
            file = fileChooser.showOpenDialog(stage);

            String ppURL = file.toURI().toURL().toString();
            Image imgLoad = new Image(ppURL);

            //pass image to imageview
            profPic.setImage(imgLoad);
        }
        catch (MalformedURLException e) {
            Logger.getLogger(profPic.toString());
        }

        /*
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Upload Profile Picture");
        TextField txtForPic = new TextField();
        String path = txtForPic.getText();
        String path2 = txtForPic.getText();

        Image img = new Image(path);
        profPic.setImage(img);

        JFXButton bttn = new JFXButton("Done", profPic);
        bttn.setOnAction(event1-> getHostServices().showDocument(path2));
        */
    }

}
