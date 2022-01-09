package avengers.whois.forchat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import avengers.whois.forchat.ChatMessage;
import avengers.whois.forchat.ChatMessage.MessageType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + " joined chat.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}