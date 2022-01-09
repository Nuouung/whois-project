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

    public long initChatList() {
        ChatE a = ChatE.builder().build();
        long chatNo = cr.save(a).getChatNo();
        System.out.println("ChatRoom List initialized : " + chatNo);
        return chatNo;
    }

    public void getChats(Model model, long chatserverNo) {
        List<RoomE> raw = rr.findBychatBox_chatNo(chatserverNo).stream().toString();
    }
}
