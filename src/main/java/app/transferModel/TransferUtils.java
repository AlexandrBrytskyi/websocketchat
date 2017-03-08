package app.transferModel;


import app.model.Message;

public class TransferUtils {

    public static SocketMessage toSocketMessage(Message message) {
        return new SocketMessage(message.getChat().getRoom().getName().toString(),
                message.getMessageContent(),
                message.getTimeSent() == null ? null : message.getTimeSent(),
                message.getSender() == null ? null : message.getSender().getName(),
                message.getReciever() == null ? null : message.getReciever().getName());
    }
}
