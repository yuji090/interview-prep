package com.zoro.interviewprep.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {
    private String senderName;
    private String content;
    private String timestamp;
}
