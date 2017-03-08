package app.dao;


import app.model.User;

public interface UserDao {

    User create(User user);

    User get(String name);

    void delete(String id);

    User update (User user);

}
