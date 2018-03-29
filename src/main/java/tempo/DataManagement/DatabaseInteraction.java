package tempo.DataManagement;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.ArrayList;

public class DatabaseInteraction {

    //Singleton design pattern

    private static DatabaseInteraction instance = null;
    private Jongo jongo = null;

    public static DatabaseInteraction getInstance(){
        if(instance == null)
            instance = new DatabaseInteraction();
        return instance;
    }

    private DatabaseInteraction(){
        Jongo jongo = new Jongo(new MongoClient(new MongoClientURI("mongodb://koala_user:koala123@ds119618.mlab.com:19618/koala")).getDB("tempo"));
    }

    /**
     * Save data to the database
     * Use it as sendDataToDatabase("events", event)
     * @param collection Name of the table on the database such as events, profiles, notofications
     * @param data Data which well be saved
     */
    public <T> void sendDataToDatabase(String collection, T data){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        mongoCollection.save(data);
    }

    /**
     * Get data from the database as a class
     * Use it as Event event = getDataFromDatabase("events", "username", "Kaan", Event.class);
     * @param collection Name of the table on the database such as events, profiles, notifications
     * @param identifier Which argument you are looking at like user name or id
     * @param attribute Search argument
     * @param cls Class type of the return type
     * @return object of the data which is required
     */
    public <T> T getDataFromDatabase(String collection, String identifier, String attribute, Class<T> cls){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        T object = mongoCollection.findOne("{"+identifier+": '"+attribute+"'}").as(cls);
        return object;
    }

    /**
     * Get data from database as a list
     * Use it as ArrayList<Event> event = getDataFromDatabase("events", "username", "Kaan", Event.class);
     * @param collection Name of the table on the database such as events, profiles, notifications
     * @param identifier Which argument you are looking at like user name or id
     * @param attribute Search argument
     * @param cls Class type of the return type
     * @return Arraylist of the data which is required
     */
    public <T> ArrayList<T> getDataListFromDatabase(String collection, String identifier, String attribute, Class<T> cls){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        MongoCursor<T> all = mongoCollection.find("{"+identifier+": '"+attribute+"'}").as(cls);
        ArrayList<T> list = new ArrayList<T>();
        for(T element : all){
            list.add(element);
        }
        return list;
    }
}
