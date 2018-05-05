package tempo.EventManagement;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import tempo.DataManagement.Storage;

import java.util.*;

public class Event {
    @MongoObjectId
    @MongoId
    private String key;
    public String owner;

    public String name;
    public int type;
    public String color;
    public Date date;
    public Date duration;
    public boolean completed;
    public boolean permanent;// if the event is permanent then it is true, if not it is false which means it is a temporary event
    public boolean timeless;
}
