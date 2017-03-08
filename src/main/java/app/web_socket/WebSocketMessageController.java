package app.web_socket;

import app.model.Message;
import app.model.RoomNames;
import app.service.UserService;
import app.transferModel.SocketMessage;
import app.transferModel.TransferUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.WebSocketHandler;

@Controller
public class WebSocketMessageController {


    @Autowired
    private UserService userService;






    @MessageMapping("/{room}/chat")
    @SendTo("/topic/{room}/messages")
    public SocketMessage greeting(SocketMessage message, @DestinationVariable(value = "room") String room) throws Exception {
//        System.out.println("recieved " + message);
//        System.out.println("room: " + room);
        Message recieved = userService.writeMessage(message.getSender(), message.getReciever(), message.getMessageContent(), RoomNames.valueOf(room));
        return TransferUtils.toSocketMessage(recieved);
    }

}
