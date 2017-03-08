package app.mvc_controllers.ajax;

import app.model.Message;
import app.model.RoomNames;
import app.service.UserService;
import app.transferModel.SocketMessage;
import app.transferModel.TransferUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/ajax/")
public class AJAXController {

    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;


    @RequestMapping(value = "goInRoom", method = RequestMethod.GET)
    public
    @ResponseBody
    String hello(@RequestParam(name = "userName") String userName, @RequestParam(name = "room") String room) {

        if (userService.goInRoom(userName, RoomNames.valueOf(room))) ;
        return "";
    }

    @RequestMapping(value = "getMessages", method = RequestMethod.GET)
    public
    @ResponseBody
    String loadMessages(@RequestParam(name = "room") String room,
                        @RequestParam(name = "page") int page,
                        @RequestParam(name = "size") int size) {

        List<Message> messages = userService.getMessages(RoomNames.valueOf(room), page, size);
        List<SocketMessage> socketMessages = messages
                .stream().map(m -> TransferUtils.toSocketMessage(m)).sorted(Comparator.comparing(SocketMessage::getDateSent))
                .collect(Collectors.toList());
        System.out.println("messages: " + socketMessages);
        return gson.toJson(socketMessages);
    }

}
