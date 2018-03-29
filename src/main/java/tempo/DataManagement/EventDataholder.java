package tempo.DataManagement;

import tempo.EventManagement.Event;

import java.util.ArrayList;

public class EventDataholder {
    private ArrayList<Event>  eventList = new ArrayList<>();

    public void gatherDataFromDatabase(String profileName){
        eventList = DatabaseInteraction.getInstance().getDataListFromDatabase("events", "name", profileName, Event.class);
    }
}
