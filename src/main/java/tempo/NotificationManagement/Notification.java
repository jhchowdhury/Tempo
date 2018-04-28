package tempo.NotificationManagement;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.Date;

public class Notification {
    @MongoId
    @MongoObjectId
    private String key;

    private String notificationName;
    private String message;
    private Date dateOfNotification;
    private int notificationType;



}
