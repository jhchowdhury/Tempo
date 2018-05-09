package tempo.ProfileManagement;

public class Friend {
    //variables
    private String friendID;
    private String name;
    private Profile profile;


    //methods
    public String getFriendID() {
        return friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return String.format(name +" "+profile.surname);
    }
}
