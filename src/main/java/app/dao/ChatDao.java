package app.dao;


import app.model.Chat;
import app.model.RoomNames;

import java.util.List;

public interface ChatDao {

    Chat create(Chat chat);

    Chat get(long id);

    Chat get(RoomNames roomName);

    List<Chat> getAll();

    Chat remove(long id);

    Chat update(Chat chat);


}
