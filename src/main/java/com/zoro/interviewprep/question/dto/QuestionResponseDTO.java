package com.zoro.interviewprep.question.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String topic;
    private String difficulty;
    private Boolean isSolved;
    private LocalDate createdAt;
    private String userEmail; // Extracted from User entity to avoid infinite recursion
}
