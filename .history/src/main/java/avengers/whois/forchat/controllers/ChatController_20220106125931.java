package avengers.whois.forchat.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import avengers.whois.forchat.dtos.ChatMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final static String path = "src\\main\\resources\\static\\chat_history\\";

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) throws IOException {
        appendMessageToHistory(message);
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + " joined chat.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    public void appendMessageToHistory(ChatMessage message) throws IOException {
        File history = new File(path + message.getRoomId() + ".txt");

        if (!history.exists())
            history.createNewFile();

        FileWriter fwriter = new FileWriter(history, true);
        BufferedWriter bwirter = new BufferedWriter(fwriter);
        bwirter.write(LocalDateTime.now() + "\t | " + message.getSender() + " : " + message.getMessage() + "\n");
        bwirter.close();
    }
}