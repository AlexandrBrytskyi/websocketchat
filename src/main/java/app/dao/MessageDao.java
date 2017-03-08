package app.dao;


import app.model.Message;
import app.model.RoomNames;

import java.util.Date;
import java.util.List;

public interface MessageDao {

    Message create(Message message);

    Message get(long id);

    List<Message> get(Date timeSent, String senderName);

    List<Message> get(RoomNames roomName, int page, int pageSize);

    Message delete(long id);

    Message update(Message message);

}
