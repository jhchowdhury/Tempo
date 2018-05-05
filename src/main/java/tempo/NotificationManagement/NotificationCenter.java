package tempo.NotificationManagement;

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

    public void displayNotifications(){

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
                sendNotification(n.notificationName, n.message, n.receiver, n.notificationType * -1);
            else
                sendNotification(n.notificationName, n.message, n.sender, n.notificationType * -1);
        }
        deleteNotification(id);

    }

    public void ignoreNotification(String id){
        Notification n = getNotification(id);
        if(n != null) {
            if (n.sender.equals(Storage.getInstance().getUser().profileID))
                sendNotification(n.notificationName, n.getKey(), n.receiver, n.notificationType * -1);
            else
                sendNotification(n.notificationName, n.getKey(), n.sender, n.notificationType * -1);
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
            if(n.message.equals(n1.getKey())){
                deleteNotification(n1.getKey());
                deleteNotification(n.getKey());
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
