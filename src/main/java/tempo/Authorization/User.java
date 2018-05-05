package tempo.Authorization;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class User {
    //for database
    @MongoId
    @MongoObjectId
    private String key;
    //variables
    public String username;
    public String password;
    public String profileID;

    public String getKey() {
        return key;
    }
}
