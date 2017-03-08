package app.dao.spring_data;

import app.dao.ChatDao;
import app.model.Chat;
import app.model.RoomNames;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public interface ChatRepo extends CrudRepository<Chat, Long>, ChatDao {

    @Override
    default Chat create(Chat chat) {
        return save(chat);
    }

    @Override
    default Chat get(long id) {
        return findOne(id);
    }

    @Override
    default Chat get(RoomNames roomName) {
        return findByRoom_Name(roomName);
    }

    Chat findByRoom_Name(RoomNames roomName);


    @Override
    default List<Chat> getAll() {
        return StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    default Chat update(Chat entity) {
        return save(entity);
    }

    @Override
    default Chat remove(long id) {
        Chat founded = findOne(id);
        if (null == founded) return null;
        delete(id);
        return founded;
    }
}
