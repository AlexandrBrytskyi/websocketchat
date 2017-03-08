package app.dao;


import app.model.Room;
import app.model.RoomNames;

import java.util.List;

public interface IRoomDao {

    Room create(Room room);

    Room get(RoomNames roomName);

    List<Room> getAll();

    void delete(RoomNames roomName);


}
