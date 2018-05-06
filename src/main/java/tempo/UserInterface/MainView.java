package tempo.UserInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.Storage;
import tempo.EventManagement.CalendarManager;
import tempo.EventManagement.Event;
import tempo.EventManagement.EventController;
import tempo.NotificationManagement.Notification;
import tempo.NotificationManagement.NotificationCenter;
import tempo.ProfileManagement.FriendsController;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainView implements Initializable {
    @FXML
    private CalendarView calenderView;

    @FXML
    private Label dataBar;

    @FXML
    private ListView toDoList;

    @FXML
    private ListView friends;

    private EventController ec;
    private FriendsController fc;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        CalendarManager cm = new CalendarManager();
        cm.setView(calenderView);
        ec = new EventController(cm, toDoList);
        ec.refreshEvents();
        NotificationCenter.getInstance();
        fc = new FriendsController(friends);
        fc.refreshFriendList();
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                NotificationCenter.getInstance().refreshNotification();
                System.out.println(Storage.getInstance().getUser().profileID);
                for(Notification n: Storage.getInstance().getNotificationHolder()){
                    if(n != null) {
                        if(n.sender != Storage.getInstance().getUser().profileID){
                            //NotificationCenter.getInstance().displayNotifications(n.getKey());
                            Platform.runLater(() -> {displayNotification(n.getKey());fc.refreshFriendList();});
                        }
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task,new Date(),5000l);
    }

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
        ec.removeEvent(calenderView.getSelectedEventId());
        ec.refreshEvents();
    }

    private void displayNotification(String id){
        NotificationCenter.getInstance().displayNotifications(id);
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
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add Friend Window");
        Label label1= new Label("Enter the username:");
        TextField txt1 = new TextField();
        Button button1= new Button("Add Friend");
        Label labelResult= new Label("");

        button1.setOnAction(e -> {
            fc.addFriend(txt1.getText());
            popupwindow.close();
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, txt1, button1, labelResult);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 150);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    @FXML
    private void removeAFriend(ActionEvent event) {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Remove Friend Window");
        Label label1= new Label("Enter the username:");
        ListView list1 = new ListView();
        Button button1= new Button("Remove Friend");
        Label labelResult= new Label("");
        list1.getItems().addAll("Burak", "Kaan", "Mert");
        button1.setOnAction(e -> {
            String title;
            if(!list1.getSelectionModel().isEmpty())
                title = list1.getSelectionModel().getSelectedItem().toString();

            popupwindow.close();
        });
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, list1, button1, labelResult);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 300);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
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
            Label label4= new Label("Event type:");
            final ComboBox typeComboBox = new ComboBox();
            typeComboBox.getItems().addAll(
                    "Timeless",
                    "Personal",
                    "Permanent"
            );
            typeComboBox.setValue("Timeless");
            Button button1= new Button("Create an Event");

            button1.setOnAction(e -> {
                title = txt1.getText();
                Event event = new Event();
                event.name = title;
                event.owner = Storage.getInstance().getUser().profileID;
                if(typeComboBox.getValue().toString().equals("Timeless")){
                    event.timeless = true;
                    event.permanent = false;
                    event.completed = false;
                    event.type = 1;
                } else if(typeComboBox.getValue().toString().equals("Personal")){
                    event.timeless = false;
                    event.permanent = false;
                    event.completed = false;
                    event.date = start;
                    event.duration = end;
                    event.type = 2;
                    start = new Date(txt2.getValue().getYear(), txt2.getValue().getMonthValue(), txt2.getValue().getDayOfMonth());
                    end = new Date(txt3.getValue().getYear(), txt3.getValue().getMonthValue(), txt3.getValue().getDayOfMonth());
                } else if(typeComboBox.getValue().toString().equals("Permanent")){
                    event.timeless = false;
                    event.permanent = true;
                    event.completed = false;
                    event.date = start;
                    event.duration = end;
                    event.type = 3;
                    start = new Date(txt2.getValue().getYear(), txt2.getValue().getMonthValue(), txt2.getValue().getDayOfMonth());
                    end = new Date(txt3.getValue().getYear(), txt3.getValue().getMonthValue(), txt3.getValue().getDayOfMonth());
                }
                ec.addEvent(event);
                popupwindow.close();
            });
            VBox layout= new VBox(10);
            layout.getChildren().addAll(label1, txt1, label2, txt2, label3, txt3, label4, typeComboBox, button1);
            layout.setAlignment(Pos.CENTER);
            Scene scene1= new Scene(layout, 300, 350);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
        }
    }
}
