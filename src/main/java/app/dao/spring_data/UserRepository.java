package app.dao.spring_data;


import app.dao.UserDao;
import app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, UserDao {


    @Override
    default User create(User user) {
        return save(user);
    }

    @Override
    default User get(String name) {
        return findOne(name);
    }

    @Override
    default User update(User user) {
        return save(user);
    }
}
