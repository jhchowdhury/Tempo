package tempo.ProfileManagement;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;//BufferedImage
import java.io.File;//to read File
import java.io.IOException;

public class Profile {
    @MongoId
    @MongoObjectId
    private String key;

    private int profileID;
    private String name;
    private String email;
    private String profilePictureName;
    private BufferedImage profileImage;
    private String[] preferences;

}
