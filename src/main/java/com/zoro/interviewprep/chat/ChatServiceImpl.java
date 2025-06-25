package com.zoro.interviewprep.chat;

import com.zoro.interviewprep.chat.dto.ChatMessageDTO;
import com.zoro.interviewprep.user.User;
import com.zoro.interviewprep.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatServiceImpl(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ChatMessageDTO saveMessage(String content, String senderEmail) {
        System.out.println("💾 Attempting to save message from: " + senderEmail);

        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> {
                    System.out.println("❌ No user found with email: " + senderEmail);
                    return new UsernameNotFoundException("User not found");
                });

        ChatMessage message = new ChatMessage(sender, content, LocalDateTime.now());
        chatMessageRepository.save(message);

        System.out.println("✅ Saved message from: " + sender.getName());

        return new ChatMessageDTO(sender.getName(), content, message.getTimestamp().toString());
    }



    @Override
    public List<ChatMessageDTO> getAllMessages() {
        return chatMessageRepository.findAll().stream()
                .map(msg -> new ChatMessageDTO(
                        msg.getSender().getName(),
                        msg.getContent(),
                        msg.getTimestamp().toString()
                ))
                .collect(Collectors.toList());
    }


}
