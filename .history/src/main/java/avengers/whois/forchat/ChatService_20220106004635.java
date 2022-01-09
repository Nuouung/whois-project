package avengers.whois.forchat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository rr;
    private final ChatRepository cr;

    private static long serverNo;

    public long initChatList() {
        ChatE a = ChatE.builder().build();
        long chatNo = cr.save(a).getChatNo();
        System.out.println("ChatRoom List initialized : " + chatNo);
        serverNo = chatNo;
        return chatNo;
    }

    public List<RoomE> getRoomList() {
        System.out.println("getRoomList execute");
        if (rr.findBychatbox_chatNo(serverNo).isPresent()) {
            System.out.println("1");
            return rr.findBychatbox_chatNo(serverNo).stream().toList();
        }
        System.out.println("1");
        List<RoomE> dummy = new ArrayList<>();
        System.out.println("1");
        return dummy;
    }

    public RoomE createChatRoom(String name) {
        ChatRoom a = ChatRoom.create(name);
        System.out.println(name);
        RoomE data = RoomE.builder().roomId(a.getRoomId()).roomName(name)
                .chatbox(ChatE.builder().chatNo(serverNo).build()).build();

        return rr.save(data);
    }

    public RoomE findRoomById(String roomId) {

        return rr.findByRoomId(roomId).get();
    }
}
