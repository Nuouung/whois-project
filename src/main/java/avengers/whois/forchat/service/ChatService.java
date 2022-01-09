package avengers.whois.forchat.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import avengers.whois.forchat.dtos.ChatRoom;
import avengers.whois.forchat.entities.ChatE;
import avengers.whois.forchat.entities.RoomE;
import avengers.whois.forchat.repositories.ChatRepository;
import avengers.whois.forchat.repositories.RoomRepository;
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
        System.out.println("getRoomList execute : current serverNo " + serverNo);
        if (rr.findBychatbox_chatNo(serverNo).isPresent()) {
            System.out.println("1");
            return rr.findBychatbox_chatNo(serverNo).stream().collect(Collectors.toList());
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
