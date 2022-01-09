package avengers.whois.forchat;

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

    public void getChats(Model model, long chatserverNo) {
        List<RoomE> raw = rr.findBychatBox_chatNo(chatserverNo).stream().toList();
        model.addAttribute("chatList", raw);
    }

    public List<RoomE> getRoomList() {
        return rr.findBychatBox_chatNo(serverNo).stream().toList();
    }

    public RoomE createChatRoom(String name) {
        ChatRoom a = ChatRoom.create(name);
        RoomE data = RoomE.builder().roomId(a.getRoomId()).roomName(a.getRoomName())
                .chatbox(ChatE.builder().chatNo(serverNo).build()).build();

        return rr.save(data);
    }

    public RoomE findRoomById(String roomId) {

        return rr.findByRoomId(roomId).get();
    }
}
