package avengers.whois.forchat.dtos;

import java.io.Serializable;
import java.util.UUID;

import avengers.whois.forchat.entities.ChatE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String roomName;

    private ChatE chatbox;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = name;
        return chatRoom;
    }
}