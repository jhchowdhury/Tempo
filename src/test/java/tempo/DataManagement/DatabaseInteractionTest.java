package tempo.DataManagement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseInteractionTest {

    @BeforeAll
    static void initAll(){
        DatabaseInteraction db = DatabaseInteraction.getInstance();
    }

    @Test
    void sendDataToDatabase() {
        System.out.println("This test method should be run");

    }

    @Test
    void getDataFromDatabase() {
    }

    @Test
    void getDataListFromDatabase() {
    }

}