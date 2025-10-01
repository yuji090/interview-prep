package com.zoro.interviewprep.chat;

import com.zoro.interviewprep.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    @Setter
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Setter
    private LocalDateTime timestamp;

    public ChatMessage() {
    }

    public ChatMessage(User sender, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    // ✅ Getters and setters

}
