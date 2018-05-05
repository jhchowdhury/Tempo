package tempo.UserInterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import netscape.javascript.JSObject;
import tempo.DataManagement.Storage;
import tempo.EventManagement.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarView extends Parent {

    //####################### Instance variables #######################
    private JSObject doc;
    private WebView webView;
    private WebEngine webEngine;
    private boolean ready;
    private String selectedEventId;
    //####################################################################



    public CalendarView(){
        initView();
        initCommunication();
        Screen screen = Screen.getPrimary();
        webView.setPrefSize(1040, 660);
        getChildren().add(webView); // Will be change as JavaFx Elements change
    }

    private void initView(){
        ready = false;

        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("web/Calendar.html").toExternalForm());
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
        {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    ready = true;
                }
            }
        });
    }

    private void initCommunication(){
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
        {
            @Override
            public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    doc = (JSObject) webEngine.executeScript("window");
                    doc.setMember("app",CalendarView.this); // Set app variable into Javascript code which is referring to this class
                }
            }
        });
    }

    private void invokeJS(final String jsCode) {
        if(ready) {
            // If initialize succeeded
            doc.eval(jsCode); // Add and run the script
        }
        else {
            // Check again, If everything is ok, eval the script
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>()
            {
                @Override
                public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                    final Worker.State oldState,
                                    final Worker.State newState)
                {
                    if (newState == Worker.State.SUCCEEDED)
                    {
                        doc.eval(jsCode); // Add and run the script
                    }
                }
            });
        }
    }

    public void addEvent(String eventID, String eventTitle, Date start, Date end){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String startDate = df.format(start);
        String endDate = df.format(end);
        invokeJS("addEvent('"+eventID +"', '"+ eventTitle +"', "+ start.getYear() +", "+ start.getMonth()+", "+ start.getDate() +", "+ end.getYear() +", "+ end.getMonth()+", "+ end.getDate()+");");
    }

    public void removeEvent(int eventID){
        invokeJS("removeEvent("+ eventID + ")");
    }

    public void changeEvent(int eventID, String eventTitle, Date start, Date end){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String startDate = df.format(start);
        String endDate = df.format(end);
        invokeJS("changeEvent("+ eventTitle +", "+ startDate + ", " + endDate +")");
    }

    public String getSelectedEventId() {
        return selectedEventId;
    }

    public void setSelectedEventId(String selectedEventId) {
        this.selectedEventId = selectedEventId.toString();
    }

    public void clearEvent(){
        invokeJS("clearEvent()");
    }

    public void handleEventOnChange(String id, int sy, int sm, int sd, int ey, int em, int ed){
        Event event = new Event();
        event.setKey(id);
        event.date = new Date();
        System.out.println(sd);
        event.date.setYear(sy);
        event.date.setMonth(sm);
        event.date.setDate(sd);
        event.duration = new Date();
        event.duration.setYear(ey);
        event.duration.setMonth(em);
        event.duration.setDate(ed);
        Storage.getInstance().changeEvent(id, event);
        System.out.println("works!");
    }
}
