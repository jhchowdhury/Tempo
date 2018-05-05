package tempo.ProfileManagement;
import tempo.DataManagement.Storage;


public class UserProfileController {
    private Profile profile;

    public void changeUserName(String newName) {
        profile.name = newName;
    }


    public void changeEmail(String newEmail) {
        profile.email = newEmail;
    }

    public void changePreferences(String[] newPrefs) {

    }

    public void loadPreferences () {

    }

    public void addFriend(Friend newFriend ) {

    }

    public void removeFriend(Friend removeddFriend) {

    }

    public void changeName(String newName) {
        profile.name = newName;
    }

    public void changeSurname(String newSurname){
        profile.surname = newSurname;
    }

}