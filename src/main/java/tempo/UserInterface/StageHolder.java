package tempo.UserInterface;

import javafx.stage.Stage;

public class StageHolder {
    private static StageHolder instance = null;

    public static Stage stage;

    public static StageHolder getInstance(){
        if(instance == null)
            instance = new StageHolder();
        return instance;
    }
}
