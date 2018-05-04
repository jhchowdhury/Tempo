package tempo.Authorization;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class User {
    @MongoId
    @MongoObjectId
    private String key;

    public String username;
    public String password;
    public String profileID;

    public String getKey() {
        return key;
    }
}
