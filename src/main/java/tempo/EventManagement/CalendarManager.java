package tempo.EventManagement;

import tempo.DataManagement.CommunicationHelper;
import tempo.UserInterface.CalendarView;

import java.util.ArrayList;

public class CalendarManager {

    private ArrayList<Event> data;
    private CalendarView view;

    public void setView(CalendarView view){
        this.view = view;
    }

    public void setupCalendar(){
        for(Event e: data){
            if(!e.timeless)
                view.addEvent(e.getKey().hashCode(), e.name, e.date, e.duration);
        }
    }

    public void setCalendarData(ArrayList<Event> data){
        this.data = data;
    }

    public void clearCalendar(){
        view.clearEvent();
    }

}
