package tempo.EventManagement;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.util.*;

public abstract class Event {
    @MongoObjectId
    @MongoId
    private String key;

    String name;
    int type;
    String color;
    Date date;
    Date duration;
    boolean completed;

}
