package tempo.SmartEventManagement;

import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;
import tempo.EventManagement.Event;
import tempo.EventManagement.EventController;
import tempo.NotificationManagement.NotificationCenter;

public class SmartEventController {

    private static SmartEventController instance = null;

    public static SmartEventController getInstance(){
        if(instance == null)
            instance = new SmartEventController();
        return instance;
    }

    public void refreshSmartEvents(){

    }

    public void addSmartEvent(SmartEvent smartEvent){
        DatabaseInteraction.getInstance().sendDataToDatabase("smartevents", smartEvent);
        NotificationCenter.getInstance().sendNotification("Smart Event Request", smartEvent.getKey(), smartEvent.getFriendID(), 3);
    }

    public void solidifySmartEvent(SmartEvent smartEvent) throws CloneNotSupportedException {
        Event event = DatabaseInteraction.getInstance().getDataFromDatabaseByID("events", smartEvent.getEventID(), Event.class);
        event = (Event) event.clone();
        event.setKey(null);
        Storage.getInstance().addEvent(event);
    }
}
