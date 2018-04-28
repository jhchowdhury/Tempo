package tempo.ProfileManagement;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Profile {
    @MongoId
    @MongoObjectId
    private String key;

    public int profileID;
    public String name;
    public String email;
    public String profilePictureName;
    public String[] preferences;

}
