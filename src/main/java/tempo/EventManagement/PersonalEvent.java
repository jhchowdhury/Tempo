package tempo.EventManagement;

import java.util.Date;

public class PersonalEvent extends Event{
    private boolean permanent;// if the event is permanent then it is true, if not it is false which means it is a temporary event
    private int eventWeight; //importance of the event
}
