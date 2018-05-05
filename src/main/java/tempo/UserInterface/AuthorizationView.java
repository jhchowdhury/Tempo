package tempo.UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.Authorization.LoginManager;
import tempo.Authorization.RegisterManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AuthorizationView {
    //FXMLs
    @FXML
    private TextField txtLoginUsername;

    @FXML
    private PasswordField txtLoginPassword;

    @FXML
    private TextField txtSignupUsername;

    @FXML
    private TextField txtSignupName;

    @FXML
    private TextField txtSignupSurname;

    @FXML
    private TextField txtSignupEmail;

    @FXML
    private PasswordField txtSignupPassword;

    @FXML
    private PasswordField txtSignupRepassword;

    @FXML
    private Label lblLoginStatus;

    @FXML
    private Label lblRegisterStatus;

    //methods for FXML

    @FXML
    private void Login(ActionEvent event) throws Exception{
        LoginManager login = new LoginManager();
        login.setUsername(txtLoginUsername.getText());
        login.setPassword(txtLoginPassword.getText());
        if(login.login()){
            lblLoginStatus.setText("Sign in succesful!");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainView.fxml"));
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
    private void Register(ActionEvent event) throws Exception{
        if(!(txtSignupName.getText().equals("") && txtSignupSurname.getText().equals("") && txtSignupEmail.getText().equals("") && txtSignupUsername.getText().equals("") && txtSignupPassword.getText().equals(""))){
            if(txtSignupPassword.getText().equals(txtSignupRepassword.getText())){
                RegisterManager register = new RegisterManager(txtSignupName.getText(), txtSignupSurname.getText(), txtSignupEmail.getText(), txtSignupUsername.getText(), txtSignupPassword.getText());
                if(register.register()){
                    lblRegisterStatus.setText("Register is succesful!");
                }
                else{
                    lblRegisterStatus.setText("Register is unsuccesful!");
                }
            }
            else{
                lblRegisterStatus.setText("Rewrite passwords.");
            }
        }
        else{
            lblRegisterStatus.setText("Fill everything!");
        }
    }
}
