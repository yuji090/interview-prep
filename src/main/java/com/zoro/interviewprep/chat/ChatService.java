package com.zoro.interviewprep.chat;

import com.zoro.interviewprep.chat.dto.ChatMessageDTO;

import java.util.List;

public interface ChatService {
    ChatMessageDTO saveMessage(String content, String senderEmail);
    List<ChatMessageDTO> getAllMessages();
}
