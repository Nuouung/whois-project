package avengers.whois.forchat.controllers;

import java.io.File;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import avengers.whois.forchat.dtos.ChatMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final static String path = "/chat_history/";

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + " joined chat.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    public void addHistory(ChatMessage message) {
        File history = new File(path + message.getRoomId() + ".txt");
    }
}