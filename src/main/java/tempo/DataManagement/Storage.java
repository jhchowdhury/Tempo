package tempo.DataManagement;
import tempo.NotificationManagement.Notification;
import tempo.ProfileManagement.Profile;
import tempo.EventManagement.Event;
import java.util.ArrayList;

public class Storage extends Event{
    private ArrayList<Event> eventHolder = new ArrayList<>();
    private ArrayList<Profile> profileHolder = new ArrayList<>();
    private ArrayList<Notification> notificationHolder = new ArrayList<>();

    public void addEvent(Event event) {

    }

    public void addProfile( Profile profile) {

    }

    public void addNotification ( Notification notification) {

    }

    public Event getEvent( String name) {

    }

    public Profile getProfile( String username) {

    }

    public Notification getNotification ( String notif) {

    }

}
