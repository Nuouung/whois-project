package avengers.whois.forchat;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
    }
}