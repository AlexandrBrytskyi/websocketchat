package app.transferModel;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;


public class SocketMessage {

    private String room;
    private String messageContent;
    private Date dateSent;
    private String sender;
    private String reciever;

    public SocketMessage() {
    }

    public SocketMessage(String room, String messageContent, Date dateSent, String sender, String reciever) {
        this.room = room;
        this.messageContent = messageContent;
        this.dateSent = dateSent;
        this.sender = sender;
        this.reciever = reciever;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketMessage that = (SocketMessage) o;

        if (room != null ? !room.equals(that.room) : that.room != null) return false;
        if (messageContent != null ? !messageContent.equals(that.messageContent) : that.messageContent != null)
            return false;
        if (dateSent != null ? !dateSent.equals(that.dateSent) : that.dateSent != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        return reciever != null ? reciever.equals(that.reciever) : that.reciever == null;
    }

    @Override
    public int hashCode() {
        int result = room != null ? room.hashCode() : 0;
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        result = 31 * result + (dateSent != null ? dateSent.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (reciever != null ? reciever.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SocketMessage{" +
                "room='" + room + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", dateSent=" + dateSent +
                ", sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                '}';
    }
}
