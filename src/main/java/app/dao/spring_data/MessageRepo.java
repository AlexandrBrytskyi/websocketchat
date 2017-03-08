package app.dao.spring_data;

import app.dao.MessageDao;
import app.model.Message;
import app.model.RoomNames;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepo extends PagingAndSortingRepository<Message, Long>, MessageDao {

    @Override
    default Message create(Message message) {
        return save(message);
    }

    @Override
    default Message get(long id) {
        return findOne(id);
    }

    @Override
    default List<Message> get(Date timeSent, String senderName) {
        return findByTimeSentAndSenderName(timeSent, senderName);
    }

    List<Message> findByTimeSentAndSenderName(Date timeSent, String name);


    @Override
    default List<Message> get(RoomNames roomName, int page, int pageSize) {
        return findByChat_Room_Name(roomName, new PageRequest(page-1, pageSize, new Sort(new Sort.Order(Sort.Direction.DESC,"timeSent" ))));
    }

    List<Message> findByChat_Room_Name(RoomNames roomNames, Pageable pageable);

    @Override
    default Message update(Message entity) {
        return save(entity);
    }


    @Override
    default Message delete(long id) {
        Message founded = findOne(id);
        if (null == founded) return null;
        delete(founded);
        return founded;
    }
}
