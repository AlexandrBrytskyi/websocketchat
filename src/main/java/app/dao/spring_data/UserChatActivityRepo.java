package app.dao.spring_data;

import app.dao.UserChatActivityDao;
import app.model.RoomNames;
import app.model.User;
import app.model.UserInChatActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatActivityRepo extends CrudRepository<UserInChatActivity, Long>, UserChatActivityDao {

    @Override
    default UserInChatActivity create(UserInChatActivity activity) {
        return save(activity);
    }

    @Override
    default UserInChatActivity get(long id) {
        return findOne(id);
    }

    @Override
    @Query(value = "delete from UserInChatActivity a where a.chat.room.name = :roonName")
    UserInChatActivity delete(@Param("roonName") RoomNames roomName);



}
