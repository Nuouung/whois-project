package avengers.whois.forchat;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository rr;
    private final ChatRepository cr;

    public void initChatList() {
        ChatE a = ChatE.builder().build();
        long chatNo = cr.save(a).getChatNo();
        System.out.println(chatNo);
    }
}
