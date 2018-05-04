package tempo.Authorization;

import tempo.DataManagement.DatabaseInteraction;
import tempo.ProfileManagement.Profile;

import java.security.NoSuchAlgorithmException;

public class RegisterManager {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    public RegisterManager(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void register() throws NoSuchAlgorithmException {
        User user = new User();
        user.username = name;
        user.password = new String(java.security.MessageDigest.getInstance("MD5").digest(password.getBytes()));
        DatabaseInteraction.getInstance().<User>sendDataToDatabase("users", user);
        Profile profile = new Profile();
        DatabaseInteraction.getInstance().<Profile>sendDataToDatabase("profiles", profile);
        user.profileID = profile.getKey();
        DatabaseInteraction.getInstance().updateDataFromDatabase("users", "key", user.getKey(), user);
        profile.profileID = profile.getKey();
        DatabaseInteraction.getInstance().updateDataFromDatabase("profiles", "key", profile.getKey(), profile);
    }

    public void operation(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
