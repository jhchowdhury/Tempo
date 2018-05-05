package tempo.DataManagement;

import tempo.EventManagement.Event;
import tempo.NotificationManagement.Notification;
import tempo.ProfileManagement.Friend;
import tempo.ProfileManagement.Profile;

import java.util.ArrayList;

public class CommunicationHelper {

    private static CommunicationHelper instance = null;

    //methods
    public static CommunicationHelper getInstance(){
        if(instance == null)
            instance = new CommunicationHelper();
        return instance;
    }

    public void initiateDatabase(){
        DatabaseInteraction.getInstance();
        fillProfileHolder();
        fillEventHolder();
        fillNotificationHolder();
        fillFriendsHolder();
    }

    public void checkLogin(){

    }

    public void createNewUser(){

    }

    public void fillNotificationHolder(){
        ArrayList<Notification> list = DatabaseInteraction.getInstance().<Notification>getDataListFromDatabase("notificiations", "reciever", Storage.getInstance().user.profileID, Notification.class);
        if(list == null)
            return;
        Storage.getInstance().setNotificationHolder(list);
    }

    public void fillProfileHolder(){
        Profile user = DatabaseInteraction.getInstance().getDataFromDatabase("profiles", "profileID", Storage.getInstance().getUserKey(), Profile.class );
        if(user == null)
            return;
        Storage.getInstance().user = user;
    }

    public void fillEventHolder(){
        ArrayList<Event> list = DatabaseInteraction.getInstance().<Event>getDataListFromDatabase("events", "owner", Storage.getInstance().user.profileID, Event.class);
        if(list == null)
            return;
        Storage.getInstance().setEventHolder(list);
    }

    public void fillFriendsHolder(){
        for(String a: Storage.getInstance().user.friends){
            Profile temppf = DatabaseInteraction.getInstance().<Profile>getDataFromDatabase("profiles","profileID", a, Profile.class);
            Friend temp = new Friend();
            temp.setName(temppf.name);
            temp.setProfile(temppf);
            temp.setFriendID(temppf.profileID);
            Storage.getInstance().getFriendsHolder().add(temp);
        }
    }

    public void addEventToDatabase(Event event){
        event.owner = Storage.getInstance().user.profileID;
        DatabaseInteraction.getInstance().sendDataToDatabase("events", event);
    }

    public void sendNotificationToDatabase(Notification noti, String receiver){
        noti.sender = Storage.getInstance().user.profileID;
        noti.receiver = receiver;
        DatabaseInteraction.getInstance().sendDataToDatabase("notifications", noti);
    }

    public void updateProfile(Profile profile){
        DatabaseInteraction.getInstance().<Profile>updateDataFromDatabase("Profile", "profileID", Storage.getInstance().user.profileID, profile);
    }
}
