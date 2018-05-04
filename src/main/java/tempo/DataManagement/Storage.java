package tempo.DataManagement;
import tempo.NotificationManagement.Notification;
import tempo.ProfileManagement.Friend;
import tempo.ProfileManagement.Profile;
import tempo.EventManagement.Event;
import java.util.ArrayList;

public class Storage{

    private static Storage instance = null;

    public static Storage getInstance(){
        if(instance == null)
            instance = new Storage();
        return instance;
    }

    public Profile user;
    private String userKey;
    private ArrayList<Event> eventHolder = new ArrayList<>();
    private ArrayList<Profile> profileHolder = new ArrayList<>();
    private ArrayList<Notification> notificationHolder = new ArrayList<>();
    private ArrayList<Friend> friendsHolder = new ArrayList<>();

    public ArrayList<Friend> getFriendsHolder() {
        return friendsHolder;
    }

    public void setFriendsHolder(ArrayList<Friend> friendsHolder) {
        this.friendsHolder = friendsHolder;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public ArrayList<Event> getEventHolder() {
        return eventHolder;
    }

    public void setEventHolder(ArrayList<Event> eventHolder) {
        this.eventHolder = eventHolder;
    }

    public ArrayList<Profile> getProfileHolder() {
        return profileHolder;
    }

    public void setProfileHolder(ArrayList<Profile> profileHolder) {
        this.profileHolder = profileHolder;
    }

    public ArrayList<Notification> getNotificationHolder() {
        return notificationHolder;
    }

    public void setNotificationHolder(ArrayList<Notification> notificationHolder) {
        this.notificationHolder = notificationHolder;
    }

    public void addEvent(Event event) {
        eventHolder.add(event);
        CommunicationHelper.getInstance().addEventToDatabase(event);
    }

    public void addNotification (Notification notification) {
        notificationHolder.add(notification);
        CommunicationHelper.getInstance().sendNotificationToDatabase(notification,notification.receiver);
    }

    public Event getEvent(String name) {
        for(Event e: eventHolder){
            if(e.name.equals(name))
                return e;
        }
        return null;
    }


    public Notification getNotification (String notif) {
        return null;
    }
}
