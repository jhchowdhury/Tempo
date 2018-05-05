package tempo.EventManagement;

import javafx.scene.control.ListView;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.Storage;


public class EventController {

    private CalendarManager cm;
    private ListView todoList;

    public EventController(CalendarManager cm, ListView todoList) {
        this.cm = cm;
        this.todoList = todoList;
    }

    public void refreshEvents(){
        cm.clearCalendar();
        CommunicationHelper.getInstance().fillEventHolder();
        cm.setCalendarData(Storage.getInstance().getEventHolder());
        cm.setupCalendar();
        fillTodoList();
    }

    public void addEvent(Event event){
        Storage.getInstance().addEvent(event);
        refreshEvents();
    }

    public void removeEvent(String eventID){
        Storage.getInstance().removeEvent(eventID);
        refreshEvents();
    }


    public void fillTodoList(){
        todoList.getItems().clear();
        for(Event e: Storage.getInstance().getEventHolder()){
            if(e.timeless)
                todoList.getItems().add(e);
        }
    }
}
