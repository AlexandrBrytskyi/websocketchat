package app.service;


import app.model.Chat;
import app.model.RoomNames;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

@Component
public class ChatContext {

    private TreeMap<RoomNames, Chat> chatMap = new TreeMap<RoomNames, Chat>();

    public TreeMap<RoomNames, Chat> getChatMap() {
        return chatMap;
    }

    public void setChatMap(TreeMap<RoomNames, Chat> chatMap) {
        this.chatMap = chatMap;
    }
}
