package tempo.ProfileManagement;


public class UserProfileController {
    private Profile profile;

    public void changeUserName(String newName){
        profile.name = newName;
    }
}
