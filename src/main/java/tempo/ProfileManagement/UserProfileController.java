package tempo.ProfileManagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class UserProfileController {
    private Profile profile;

    @FXML
    private Label lblProfileChanges;

    public void changeUserName(String newName) {
        if ( newName.length() < 6) {
            lblProfileChanges.setText("Unsuccessful! Invalid type of new username! ");
        }
        else {
            profile.name = newName;
        }
    }


    public void changeEmail(String newEmail) {
        if (newEmail.isEmpty()) {
            lblProfileChanges.setText("Unsuccessful! Invalid type of new email! ");
        }
        else {
            profile.email = newEmail;
        }
    }

    public void changePreferences(String[] newPrefs) {
        if (newPrefs.length < 1) {
            lblProfileChanges.setText("Unsuccessful!");
        }
        else {

        }
    }

    public void loadPreferences () {
        if ( profile.preferences.length < 1) {
            lblProfileChanges.setText("Unsuccessful!");
        }
        else {

        }
    }

    public void addFriend(Friend newFriend ) {

    }

    public void removeFriend(Friend removeddFriend) {

    }

    public void changeName(String newName) {
        if (newName.length() < 3) {
            lblProfileChanges.setText("Unsuccessful! Invalid type of new name! ");
        }
        else {
            profile.name = newName;
        }
    }

    public void changeSurname(String newSurname){
        if (newSurname.length() < 2) {
            lblProfileChanges.setText("Unsuccessful! Invalid type of new surname!");
        }
        else {
            profile.surname = newSurname;
        }
    }

}