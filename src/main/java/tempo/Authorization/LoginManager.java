package tempo.Authorization;

import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;
import tempo.ProfileManagement.Profile;

import java.security.NoSuchAlgorithmException;

public class LoginManager {
    private String username;
    private String password;

    public LoginManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginManager() {
    }

    public boolean login() throws NoSuchAlgorithmException {
        String encodedPassword = new String(java.security.MessageDigest.getInstance("MD5").digest(password.getBytes()));
        User user = DatabaseInteraction.getInstance().getDataFromDatabase("users", "username", username, User.class);
        if(user == null){
            return false;
        }
        if(user.password.equals(encodedPassword)){
            Profile profile = DatabaseInteraction.getInstance().getDataFromDatabase("profiles", "profileID", user.profileID, Profile.class);
            Storage.getInstance().setUser(profile);
            System.out.println("Successful");
            return true;
        } else {
            System.out.println("Password is wrong!");
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
