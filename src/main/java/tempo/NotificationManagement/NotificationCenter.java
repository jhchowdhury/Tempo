package tempo.NotificationManagement;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.Authorization.User;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;
import tempo.ProfileManagement.Friend;
import tempo.ProfileManagement.Profile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class NotificationCenter {

    private static NotificationCenter instance = null;

    public static NotificationCenter getInstance(){
        if(instance == null)
            instance = new NotificationCenter();
        return instance;
    }

    private boolean isShow = false;

    public void displayNotifications(String id){
        Notification n = getNotification(id);
        if(isShow)
            return;
        isShow = true;
        if(n != null) {
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle(n.notificationName);
            Label label1 = new Label(n.message);
            Button button1 = new Button("Accept");
            Button button2 = new Button("Ignore");

            button1.setOnAction(e -> {
                approveNotification(id);
                refreshNotification();
                isShow = false;
                popupwindow.close();
            });
            button2.setOnAction(e -> {
                ignoreNotification(id);
                refreshNotification();
                isShow = false;
                popupwindow.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(label1, button1, button2);
            layout.setAlignment(Pos.CENTER);
            Scene scene1 = new Scene(layout, 300, 150);
            popupwindow.setScene(scene1);
            popupwindow.showAndWait();
        }
    }

    public void deleteNotification(String id){
        Notification n = getNotification(id);
        if(n != null){
            Storage.getInstance().getNotificationHolder().remove(n);
            DatabaseInteraction.getInstance().removeDataFromDatabasebyID("notificiations", n.getKey());
        }
    }

    public Notification getNotification(String id){
        for(Notification n: Storage.getInstance().getNotificationHolder()){
            if(n.getKey().equals(id))
                return n;
        }
        return null;
    }

    public void approveNotification(String id){
        Notification n = getNotification(id);
        if(n != null) {
            if (n.receiver.equals(Storage.getInstance().getUser().profileID))
                sendNotification("accapted", n.message, n.sender, n.notificationType * -1);
            switch(n.notificationType){
                case 1:
                    Storage.getInstance().addFriend(n.sender);
                    break;
            }
        }
        deleteNotification(n.getKey());
    }

    public void ignoreNotification(String id){
        Notification n = getNotification(id);
        if(n != null) {
            deleteNotification(n.getKey());
        }

    }

    public boolean sendFriendRequest(String username){
        User friend = DatabaseInteraction.getInstance().getDataFromDatabase("users", "username", username, User.class);
        if(friend == null)
            return false;
        sendNotification("Friend Request", Storage.getInstance().getUser().name+" wants to be your friend!", friend.profileID, 1);
        return true;
    }

    public void refreshNotification(){
        CommunicationHelper.getInstance().fillNotificationHolder();
        for (Notification n: Storage.getInstance().getNotificationHolder()) {
            switch(n.notificationType){
                case -1:
                    Storage.getInstance().addFriend(n.sender);
                    deleteNotification(n.getKey());
                    break;
            }
        }

    }

    public void sendNotification(String title, String massage, String receiver, int type){
        Notification notification = new Notification();
        notification.sender = Storage.getInstance().getUser().profileID;
        notification.receiver = receiver;
        notification.dateOfNotification = new Date();
        notification.message = massage;
        notification.notificationType = type;
        notification.notificationName = title;
        DatabaseInteraction.getInstance().sendDataToDatabase("notificiations", notification);
        Storage.getInstance().getNotificationHolder().add(notification);
    }
}
