package app.service;

import app.Application;
import app.exceptions.UserAlreadyExistsException;
import app.exceptions.UserNotInRoomException;
import app.exceptions.WrongUserCredentialsException;
import app.model.Message;
import app.model.RoomNames;
import app.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    static String userName = "Vasiliy_" + System.currentTimeMillis();
    static String password = "qwertyuiop";


    @Test
    public void _001registerTest() {
        User registered = null;
        try {
            registered = userService.register(userName, password);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            assertTrue("Cant be same user registered", false);
        }

        assertNotNull("Registered user must be not null " + registered);

        try {
            registered = userService.register(userName, password);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            assertTrue("Cant be same user registered", true);
        }
    }

    @Test
    public void _002loginTest() {
        User logged;
        try {
            logged = userService.login(userName, password);
            assertNotNull("Logined user must not be null", logged);
        } catch (WrongUserCredentialsException e) {
            assertTrue("Expected user must login, but have " + e.getMessage(), false);
        }


        try {
            logged = userService.login("wrong name", password);
            assertTrue("expected wrong user name", false);
        } catch (WrongUserCredentialsException e) {
            assertTrue("expected wrong user name", true);
            e.printStackTrace();
        }

    }


    @Test
    public void _003goInRoomTest() {
        try {
            assertTrue(userService.goInRoom(userService.login(userName, password).getName(), RoomNames.Games));
        } catch (WrongUserCredentialsException e) {
            assertTrue("Expected success login but have " + e.getMessage(), false);
            e.printStackTrace();
        }
    }

    @Test
    public void _004_2usersloginGoInRoomWriteMessagesGetMessagesTest() {
//       registering 2 users
        String user1Name = "" + System.currentTimeMillis();
        String user2Name = "" + System.currentTimeMillis()+10;
        String pass = "12345";
        User user1 = null;
        User user2;
        try {
            user1 = userService.register(user1Name, pass);
            user2 = userService.register(user2Name, pass);
        } catch (UserAlreadyExistsException e) {
            assertFalse("Register should be success, but have " + e.getMessage(), true);
            e.printStackTrace();
        }

//        login
        try {
            user1 = userService.login(user1Name, pass);
            user2 = userService.login(user2Name, pass);
        } catch (WrongUserCredentialsException e) {
            assertFalse("Login should be success, but have " + e.getMessage(), true);
            e.printStackTrace();
        }

//      user 1 go in room and write message
        try {
            userService.writeMessage(user1Name, null, "first message", RoomNames.Pediki);
            assertTrue("User cant write message till he is not in the room", false);
        } catch (UserNotInRoomException e) {
            e.printStackTrace();
        }

        assertTrue("Go in room should be success", userService.goInRoom(user1.getName(), RoomNames.Pediki));


//      now writeng message while user is in room
        try {
            userService.writeMessage(user1Name, null, "first message", RoomNames.Pediki);
            userService.writeMessage(user1Name, null, "Second message", RoomNames.Pediki);
        } catch (UserNotInRoomException e) {
            assertTrue("User should be already in the room", false);
            e.printStackTrace();
        }

//        user2 go in room
        userService.goInRoom(user2Name, RoomNames.Pediki);
//        try to get messages
        List<Message> messages = userService.getMessages(RoomNames.Pediki, 1, 10);
        System.out.println(messages);
        assertTrue("There must be just 2 messages in dialog ", messages.size() == 2);


    }


}