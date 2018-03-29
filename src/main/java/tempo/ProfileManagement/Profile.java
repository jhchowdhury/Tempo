package tempo.ProfileManagement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;//BufferedImage
import java.io.File;//to read File
import java.io.IOException;

public class Profile {
    private int profileID;
    private String name;
    private String email;
    private String profilePictureName;
    private BufferedImage profileImage;
    private String[] preferences;

    public Profile(String profilePictureName) throws IOException {
        this.profilePictureName = profilePictureName;
        this.profileImage = ImageIO.read(new File(profilePictureName + ".jpg"));
    }
}
