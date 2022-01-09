package avengers.whois.forchat;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String roomName;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}