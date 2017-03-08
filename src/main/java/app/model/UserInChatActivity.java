package app.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class UserInChatActivity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "name")
    private User user;

    @ManyToOne(targetEntity = Chat.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "chat", referencedColumnName = "id")
    private Chat chat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date userJoined;

    public UserInChatActivity() {
    }

    public UserInChatActivity(User user, Chat chat) {
        this.user = user;
        this.chat = chat;
        userJoined = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Date getUserJoined() {
        return userJoined;
    }

    public void setUserJoined(Date userJoined) {
        this.userJoined = userJoined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInChatActivity that = (UserInChatActivity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "UserInChatActivity{" +
                "id=" + id +
                ", user=" + user +
                ", chat=" + chat +
                ", userJoined=" + userJoined +
                '}';
    }
}
