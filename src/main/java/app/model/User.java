package app.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;



@Entity(name = "users")
public class User {

    @Id
    private String name;

    /**
     * must be hashed
     */
    @Column
    private String password;

    @OneToMany(targetEntity = UserInChatActivity.class)
    private List<UserInChatActivity> currentChats;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserInChatActivity> getCurrentChats() {
        return currentChats;
    }

    public void setCurrentChats(List<UserInChatActivity> currentChats) {
        this.currentChats = currentChats;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
//                ", currentChats=" + currentChats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
