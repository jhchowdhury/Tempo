package tempo.DataManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tempo.EventManagement.Event;
import tempo.ProfileManagement.Profile;

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
        db.sendDataToDatabase("profiles", profile);
    }

    @Test
    void getDataFromDatabase() {
    }

    @Test
    void getDataListFromDatabase() {
    }

}