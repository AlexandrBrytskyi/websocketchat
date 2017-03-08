package app.service;


import app.exceptions.UserAlreadyExistsException;
import app.exceptions.UserNotInRoomException;
import app.exceptions.WrongUserCredentialsException;
import app.model.Message;
import app.model.RoomNames;
import app.model.User;

import java.util.List;

public interface UserService {

    User register(String name, String pass) throws UserAlreadyExistsException;

    User login(String name, String pass) throws WrongUserCredentialsException;

    boolean goInRoom(String userName, RoomNames roomName);

    boolean goOutOfRoom(String userName, RoomNames roomName);

    Message writeMessage(String sender, String reciever, String content, RoomNames roomName) throws UserNotInRoomException;

    List<Message> getMessages(RoomNames roomName, int page, int pageSize);

}
