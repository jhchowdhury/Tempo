package tempo.DataManagement;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.ArrayList;

public class DatabaseInteraction {

    //**Singleton design pattern**

    private static DatabaseInteraction instance = null;
    private Jongo jongo = null;

    public static DatabaseInteraction getInstance(){
        if(instance == null)
            instance = new DatabaseInteraction();
        return instance;
    }

    private DatabaseInteraction(){
        jongo = new Jongo(new MongoClient(new MongoClientURI("mongodb://admin:jubaidforever@ds159459.mlab.com:59459/tempo")).getDB("tempo"));
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
            if(element != null)
                list.add(element);
        }
        return list;
    }

    /**
     * Remove data from the database by identifier
     * Use it as removeDataFromDatabase("events", "username", "Kaan");
     * @param collection Name of the table on the database such as events, profiles, notifications
     * @param identifier Which argument you are looking at like user name or id
     * @param attribute Search argument
     */
    public void removeDataFromDatabase(String collection, String identifier, String attribute){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        mongoCollection.remove("{"+identifier+": '"+attribute+"'}");
    }

    public void removeDataFromDatabasebyID(String collection, String identifier){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        mongoCollection.remove("{"+identifier+"'}");
    }

    public <T> void updateDataFromDatabase(String collection, String identifier, String attribute, T data){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        mongoCollection.update("{"+identifier+": '"+attribute+"'}").with(data);
    }

    public <T> void updateDataFromDatabaseByID(String collection, String identifier, T data){
        MongoCollection mongoCollection = jongo.getCollection(collection);
        mongoCollection.update(new ObjectId(identifier)).with(data);
    }
}
