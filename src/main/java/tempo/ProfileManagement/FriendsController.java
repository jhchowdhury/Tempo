package tempo.ProfileManagement;

import javafx.scene.control.ListView;
import tempo.Authorization.User;
import tempo.DataManagement.CommunicationHelper;
import tempo.DataManagement.DatabaseInteraction;
import tempo.DataManagement.Storage;
import tempo.NotificationManagement.NotificationCenter;

public class FriendsController {

    private ListView friendList;

    public FriendsController(ListView friendList) {
        this.friendList = friendList;
    }

    public void refreshFriendList(){
        CommunicationHelper.getInstance().fillFriendsHolder();
        fillFriendList();
    }

    public void addFriend(String username){
        NotificationCenter.getInstance().sendFriendRequest(username);
    }

    public void removeFriend(String id){
        Storage.getInstance().removeFriend(id);
        NotificationCenter.getInstance().sendDeleteFriend(id);
    }

    public Friend getFriend(String username){
        return null;
    }

    public void fillFriendList(){
        friendList.getItems().clear();
        for(Friend e: Storage.getInstance().getFriendsHolder()){
            friendList.getItems().add(e);
        }
    }
}
