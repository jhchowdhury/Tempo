package tempo.EventManagement;

import java.util.Date;

public class PersonalEvent implements Event{
    private String eventName;
    private Date eventDate;
    private Date eventDuration;
    private boolean permanent;// if the event is permanent then it is true, if not it is false which means it is a temporary event
    private String eventColor;
    private int eventWeight; //importance of the event
}
