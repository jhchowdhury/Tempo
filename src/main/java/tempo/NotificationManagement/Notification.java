package tempo.NotificationManagement;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Date;

public class Notification {
    @MongoId
    @MongoObjectId
    private String key;

    public String sender;
    public String receiver;
    public String notificationName;
    public String message;
    public Date dateOfNotification;
    public int notificationType;
}
