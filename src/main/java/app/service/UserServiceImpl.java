package app.service;


import app.dao.*;
import app.exceptions.UserAlreadyExistsException;
import app.exceptions.UserNotInRoomException;
import app.exceptions.WrongUserCredentialsException;
import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IRoomDao roomDao;

    @Autowired
    private ChatDao chatDao;

    @Autowired
    private UserChatActivityDao userChatActivityDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ChatContext chatContext;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User register(String name, String pass) throws UserAlreadyExistsException {
        User founded = userDao.get(name);
        if (null != founded) throw new UserAlreadyExistsException(name);

        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(pass));

        User added = userDao.create(newUser);
        return added;
    }

    public User login(String name, String pass) throws WrongUserCredentialsException {
        User founded = userDao.get(name);
        if (null == founded || !passwordEncoder.matches(pass, founded.getPassword()))
            throw new WrongUserCredentialsException("Wrong name");
        return founded;
    }

    public boolean goInRoom(String userName, RoomNames roomName) {
        TreeMap<RoomNames, Chat> chatMap = chatContext.getChatMap();
        Chat chat1 = chatMap.get(roomName);
        if (chat1 != null && chat1.getUsersInRoom().contains(userDao.get(userName))) return true;
        Room room = roomDao.get(roomName);
        User user = userDao.get(userName);
        if (null == room) {
//            persist new room
            Room newRoom = new Room(roomName, new Date());
            room = roomDao.create(newRoom);
        }
        Chat chat = chat1;
        if (null == chat) {
            chat = chatDao.get(roomName);
            if (null == chat) {
//            persist new chat
                Chat newChat = new Chat(room, new ArrayList<Message>(), new HashSet<User>());
                chat = chatDao.create(newChat);
            }
            chatMap.put(roomName, chat);
        }

        if (null == chat.getUsersInRoom()) chat.setUsersInRoom(new HashSet<>());

        chat.getUsersInRoom().add(user);
        user.getCurrentChats().add(userChatActivityDao.create(new UserInChatActivity(user, chat)));

        chatDao.update(chat);
        userDao.update(user);
        return true;
    }

    public boolean goOutOfRoom(String userName, RoomNames roomName) {
        Chat chat = chatContext.getChatMap().get(roomName);
        User user = userDao.get(userName);

        chat.getUsersInRoom().remove(user);
        userChatActivityDao.delete(roomName);

        return true;
    }

    public Message writeMessage(String sender, String reciever, String content, RoomNames roomName) throws UserNotInRoomException {
        Chat chat = chatContext.getChatMap().get(roomName);
        User senderUser = userDao.get(sender);
        if (null == chat || !chat.getUsersInRoom().contains(senderUser))
            throw new UserNotInRoomException(roomName.toString());
        Message message = new Message(new Date(), senderUser, content, reciever == null ? null : userDao.get(reciever));
        message.setChat(chat);
        message = messageDao.create(message);
        chat.getMessages().add(message);
        return message;
    }

    public List<Message> getMessages(RoomNames roomName, int page, int pageSize) {
        List<Message> messages = messageDao.get(roomName, page, pageSize);
        return messages;
    }


}
