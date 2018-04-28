package tempo.ProfileManagement;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Profile {
    private int profileID;
    private String name;
    private String email;
    private String profilePictureName;
    private String[] preferences;

    @MongoId
    @MongoObjectId
    private String key;


}
