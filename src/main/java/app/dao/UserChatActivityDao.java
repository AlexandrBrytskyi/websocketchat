package app.dao;


import app.model.RoomNames;
import app.model.UserInChatActivity;

public interface UserChatActivityDao {


    UserInChatActivity create(UserInChatActivity activity);

    UserInChatActivity get(long id);

    UserInChatActivity delete(long id);

    UserInChatActivity delete(RoomNames roomName);

}
