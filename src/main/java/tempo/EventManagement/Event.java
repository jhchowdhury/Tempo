package tempo.EventManagement;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;
import tempo.DataManagement.Storage;

import java.util.*;

public abstract class Event {
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
}
