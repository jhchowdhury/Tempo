package tempo.DataManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tempo.EventManagement.Event;
import tempo.ProfileManagement.Profile;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseInteractionTest {

    static DatabaseInteraction db;

    @BeforeAll
    static void initAll(){
        db = DatabaseInteraction.getInstance();
    }

    @Test
    void sendDataToDatabase() {
        System.out.println("This test method should be run");
        Profile profile = new Profile();
        profile.name = "Holy Jubaid";
        profile.email = "jub@aid.com";
        db.<Profile>sendDataToDatabase("profiles", profile);
    }

    @Test
    void getDataFromDatabase() {
        Profile pf = db.<Profile>getDataFromDatabase("profiles","name","Holy Jubaid",Profile.class);
        System.out.println(pf.email);
    }

    @Test
    void getDataListFromDatabase() {
        ArrayList<Profile> pflist = db.<Profile>getDataListFromDatabase("profiles","name","Holy Jubaid", Profile.class);
        System.out.println(pflist.size());
    }

}