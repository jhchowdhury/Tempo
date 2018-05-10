package tempo.DataManagement;
import org.jongo.marshall.jackson.oid.ObjectId;
import tempo.Authorization.User;
import tempo.NotificationManagement.Notification;
import tempo.ProfileManagement.Friend;
import tempo.ProfileManagement.Profile;
import tempo.EventManagement.Event;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage{

    private static Storage instance = null;

    public static Storage getInstance(){
        if(instance == null)
            instance = new Storage();
        return instance;
    }

    public Profile user;
    private String userKey;
    public User usr;
    //Arraylists for ecent,profile,notification and friends
    private ArrayList<Event> eventHolder = new ArrayList<>();
    private ArrayList<Profile> profileHolder = new ArrayList<>();
    private ArrayList<Notification> notificationHolder = new ArrayList<>();
    private ArrayList<Friend> friendsHolder = new ArrayList<>();

    //methods

    public User getUsr() {return usr;}

    public void setUsr(User usr1) { usr = usr1;}

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

    public Event getEvent(String id) {
        for(Event e: eventHolder){
            if(e.getKey().equals(id))
                return e;
        }
        return null;
    }

    public void removeEvent(String id){
        Event e = getEvent(id);
        if(e != null) {
            eventHolder.remove(getEvent(id));
            DatabaseInteraction.getInstance().removeDataFromDatabasebyID("events", id);
        }
    }

    public void changeEvent(String key, Event event){
        Event e = getEvent(key);
        event.name = e.name;
        event.timeless = e.timeless;
        event.owner = e.owner;
        event.type = e.type;
        event.completed = e.completed;
        event.permanent = e.permanent;
        event.color = e.color;
        removeEvent(e.getKey());
        addEvent(event);
    }

    public void addFriend(String id){
        ArrayList<String> list;
        if(getUser().friends == null){
            list = new ArrayList<String>();
        }else
            list = new ArrayList<String>(Arrays.asList(getUser().friends));
        list.add(id);
        getUser().friends =  new String[list.size()];
        getUser().friends =  list.toArray(new String[0]);
        DatabaseInteraction.getInstance().updateDataFromDatabaseByID("profiles", getUser().profileID, getUser());
    }

    public void removeFriend(String friend){
        ArrayList<String> list;
        if(getUser().friends == null){
            return;
        }else
            list = new ArrayList<String>(Arrays.asList(getUser().friends));
        list.remove(friend);
        getUser().friends =  new String[list.size()];
        getUser().friends =  list.toArray(new String[0]);
        DatabaseInteraction.getInstance().updateDataFromDatabaseByID("profiles", getUser().profileID, getUser());
    }

    public void clear(){
        Profile user = null;
        String userKey = null;

        ArrayList<Event> eventHolder = new ArrayList<>();
        ArrayList<Profile> profileHolder = new ArrayList<>();
        ArrayList<Notification> notificationHolder = new ArrayList<>();
        ArrayList<Friend> friendsHolder = new ArrayList<>();
    }

    public Notification getNotification (String notif) {
        return null;
    }
}
