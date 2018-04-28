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

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    public BufferedImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(BufferedImage profileImage) {
        this.profileImage = profileImage;
    }
}
