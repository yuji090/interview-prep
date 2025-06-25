package com.zoro.interviewprep.chat;

import com.zoro.interviewprep.chat.dto.ChatMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/chat") // ✅ All REST endpoints under this path
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload String content, Principal principal) {
        System.out.println("🔥 Received WS message");

        if (principal == null || principal.getName() == null) {
            System.out.println("❌ Principal is null or name missing");
            return new ChatMessageDTO("system", "unauthorized", "now");
        }

        String senderEmail = principal.getName();
        System.out.println("✅ Authenticated user: " + senderEmail);

        return chatService.saveMessage(content, senderEmail);
    }

    @GetMapping("/messages")
    public List<ChatMessageDTO> getMessages(Principal principal) {
        System.out.println("📥 Chat history requested by: " + principal.getName());
        return chatService.getAllMessages();
    }
}
