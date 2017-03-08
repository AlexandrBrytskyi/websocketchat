package app.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Chat {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(targetEntity = Room.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "room", referencedColumnName = "name")
    private Room room;

    @OneToMany()
    private List<Message> messages;

    @Transient
    private Set<User> usersInRoom;


    public Chat() {
    }

    public Chat(Room room, List<Message> messages, Set<User> usersInRoom) {
        this.room = room;
        this.messages = messages;
        this.usersInRoom = usersInRoom;
    }

    public Set<User> getUsersInRoom() {
        return usersInRoom;
    }

    public void setUsersInRoom(Set<User> usersInRoom) {
        this.usersInRoom = usersInRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", room=" + room +
                ", messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        return id == chat.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
