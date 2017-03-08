package app.exceptions;


public class UserNotInRoomException extends Exception {

    public UserNotInRoomException(String roomName) {
        super("Not permitted to user, first go into the room " + roomName);
    }
}
