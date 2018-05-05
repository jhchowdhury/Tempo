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
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.Authorization.LoginManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainView {
    @FXML
    private CalendarView calenderView;

    @FXML
    private Label dataBar;

    @FXML
    private ListView toDoList;

    @FXML
    private ListView friends;

    @FXML
    private void openWeekly(ActionEvent event) throws Exception {
    }

    @FXML
    private void openMonthly(ActionEvent event) throws Exception {
    }

    @FXML
    private void handleEventAddBtn(ActionEvent event) {
        // Button was clicked, do something...
        dataBar.setText("Button Action\n");
        Popup pop = new Popup();
        pop.display();
    }

    @FXML
    private void eventDelete(ActionEvent event) {
    }

    @FXML
    private void handleProfileBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ProfilePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    private void addAFriend(ActionEvent event) {

    }

    @FXML
    private void removeAFriend(ActionEvent event) {

    }

    @FXML
    private void helpButton(ActionEvent event) {

    }

    private class Popup {

        public String title;
        public Date start, end;

        private void display()
        {
            Stage popupwindow=new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle("Add Event Window");
            Label label1= new Label("Enter a title:");
            TextField txt1 = new TextField();
            Label label2= new Label("Enter the start date:");
            DatePicker txt2 = new DatePicker();
            Label label3= new Label("Enter the end date:");
            DatePicker txt3 = new DatePicker();
            Button button1= new Button("Create an Event");

            button1.setOnAction(e -> {
                title = txt1.getText();
                start = new Date(txt2.getValue().getYear(), txt2.getValue().getMonthValue(), txt2.getValue().getDayOfMonth());
                end = new Date(txt3.getValue().getYear(), txt3.getValue().getMonthValue(), txt3.getValue().getDayOfMonth());
                calenderView.addEvent(1,title, start, end);
                popupwindow.close();
            });
            VBox layout= new VBox(10);
            layout.getChildren().addAll(label1, txt1, label2, txt2, label3, txt3, button1);
            layout.setAlignment(Pos.CENTER);
            Scene scene1= new Scene(layout, 300, 250);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
        }
    }
}
