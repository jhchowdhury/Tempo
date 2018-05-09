package tempo.UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tempo.Authorization.LoginManager;
import tempo.Authorization.RegisterManager;
import com.jfoenix.controls.*;

public class AuthorizationView {
    //FXMLs

    @FXML
    private JFXTextField txtLoginUsername;

    @FXML
    private JFXPasswordField txtLoginPassword;

    @FXML
    private JFXTextField txtSignupUsername;

    @FXML
    private JFXTextField txtSignupName;

    @FXML
    private JFXTextField txtSignupSurname;

    @FXML
    private JFXTextField txtSignupEmail;

    @FXML
    private JFXPasswordField txtSignupPassword;

    @FXML
    private JFXPasswordField txtSignupRepassword;

    @FXML
    private Label lblLoginStatus;

    @FXML
    private Label lblRegisterStatus;

    //methods for FXML


    @FXML
    private void back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interfaces/StartMenu.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void login(ActionEvent event) throws Exception{
        LoginManager login = new LoginManager();
        login.setUsername(txtLoginUsername.getText());
        login.setPassword(txtLoginPassword.getText());
        if(login.login()){
            lblLoginStatus.setText("Sign in succesful!");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interfaces/MainView.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else{
            lblLoginStatus.setText("Sign in unsuccesful!");
        }
    }

    @FXML
    private void register(ActionEvent event) throws Exception {
        if (!(txtSignupName.getText().equals("") && txtSignupSurname.getText().equals("") && txtSignupEmail.getText().equals("") && (txtSignupUsername.getText().equals("") && txtSignupPassword.getText().equals("")))) {
            if ((txtSignupUsername.getText().length() < 4) || (txtSignupName.getText().length() < 2) || (txtSignupSurname.getText().length() < 2)) {
                lblRegisterStatus.setText("Invalid length for username,name or surname!");
            } else {
                if (txtSignupEmail.getText().length() < 8) {
                    lblRegisterStatus.setText("Invalid email!");
                } else {
                    if (txtSignupPassword.getText().length() > 5) {
                        if (txtSignupPassword.getText().equals(txtSignupRepassword.getText())) {
                            RegisterManager register = new RegisterManager(txtSignupName.getText(), txtSignupSurname.getText(), txtSignupEmail.getText(), txtSignupUsername.getText(), txtSignupPassword.getText());
                            if (register.register()) {
                                lblRegisterStatus.setText("Register is succesful!");
                            } else {
                                lblRegisterStatus.setText("Register is unsuccesful!");
                            }
                        } else {
                            lblRegisterStatus.setText("Passwords do not match! Try again!.");
                        }
                    } else {
                        lblRegisterStatus.setText("Password should be minimum 6 characters!");
                    }

                }
            }
        } else {
            lblRegisterStatus.setText("Fill everything!");
        }
    }

    @FXML
    private void startAlreadyLogin(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interfaces/LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
