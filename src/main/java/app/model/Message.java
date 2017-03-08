package app.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timeSent;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", referencedColumnName = "name")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat", referencedColumnName = "id")
    private Chat chat;

    @Column
    private String messageContent;

    /**
     * if reciever is null than the message is sent just in room
     */
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reciever", referencedColumnName = "name")
    private User reciever;

    public Message() {
    }

    public Message(Date timeSent, User sender, String messageContent, User reciever) {
        this.timeSent = timeSent;
        this.sender = sender;
        this.messageContent = messageContent;
        this.reciever = reciever;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", timeSent=" + timeSent +
                ", sender=" + sender +
                ", messageContent='" + messageContent + '\'' +
                ", reciever=" + reciever +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id == message.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }


}
