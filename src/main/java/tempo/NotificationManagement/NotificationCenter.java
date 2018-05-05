package tempo.NotificationManagement;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;

import java.util.ArrayList;
import java.util.Date;

public class NotificationCenter {

    private static NotificationCenter instance = null;

    public static NotificationCenter getInstance(){
        if(instance == null)
            instance = new NotificationCenter();
        return instance;
    }

    public void displayNotifications(String id){
        Notification n = getNotification(id);
        if(n != null) {
            Stage popupwindow = new Stage();
            popupwindow.initModality(Modality.APPLICATION_MODAL);
            popupwindow.setTitle(n.notificationName);
            Label label1 = new Label(n.message);
            Button button1 = new Button("Accept");
            Button button2 = new Button("Ignore");

            button1.setOnAction(e -> {
                approveNotification(id);

                popupwindow.close();
            });
            button2.setOnAction(e -> {
                ignoreNotification(id);

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
            if (n.sender.equals(Storage.getInstance().getUser().profileID))
                sendNotification("accapted", n.message, n.receiver, n.notificationType * -1);
            else
                sendNotification("accapted", n.message, n.sender, n.notificationType * -1);
        }
        deleteNotification(id);

    }

    public void ignoreNotification(String id){
        Notification n = getNotification(id);
        if(n != null) {
            if (n.sender.equals(Storage.getInstance().getUser().profileID))
                sendNotification("ignored", n.getKey(), n.receiver, n.notificationType * -1);
            else
                sendNotification("ignored", n.getKey(), n.sender, n.notificationType * -1);
        }
        deleteNotification(id);
    }

    public void refreshNotification(){
        CommunicationHelper.getInstance().fillNotificationHolder();
        ArrayList<Notification> list1 = new ArrayList<Notification>();
        for(Notification n : Storage.getInstance().getNotificationHolder()){
            if(n.notificationType < 0){
                list1.add(n);
            }
        }
        for(Notification n : Storage.getInstance().getNotificationHolder()){
            for(Notification n1 : list1)
            if(n.message.equals(n1.getKey()) && n.notificationType > 0){
                deleteNotification(n1.getKey());
                deleteNotification(n.getKey());
                if(n.notificationName == "accepted"){
                    switch(n.notificationType){
                        case 1:
                            //friend request accepted
                            break;
                        case 2:
                            //smartevent accepted
                            break;
                    }
                }else if(n.notificationName == "ignored"){
                    switch(n.notificationType){
                        case 1:
                            //friend request ignored
                            // do nothing
                            break;
                        case 2:
                            //smartevent ignored
                            //remove smart event ghost
                            break;
                    }
                }
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
