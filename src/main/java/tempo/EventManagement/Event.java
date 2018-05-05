package tempo.EventManagement;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import tempo.DataManagement.Storage;

import java.util.*;

public class Event {
    //for database
    @MongoObjectId
    @MongoId
    private String key;
    public String owner;
    //variables
    public String name;
    public int type;
    public String color;
    public Date date;
    public Date duration;
    public boolean completed;
    public boolean permanent;// if the event is permanent then it is true, if not it is false which means it is a temporary event
    public boolean timeless;

    public String getKey(){
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return String.format(name);
    }
}
