package app.dao.spring_data;

import app.dao.IRoomDao;
import app.model.Room;
import app.model.RoomNames;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface IRoomRepo extends CrudRepository<Room, RoomNames>, IRoomDao {

    @Override
    default Room create(Room room) {
        return save(room);
    }

    @Override
    default Room get(RoomNames roomName) {
        return findOne(roomName);
    }

    @Override
    default List<Room> getAll() {
        return StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList());
    }

}
