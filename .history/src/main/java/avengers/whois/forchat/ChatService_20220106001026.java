package avengers.whois.forchat;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository rr;
    private final ChatRepository cr;
}
