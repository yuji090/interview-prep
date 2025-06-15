package com.zoro.interviewprep.question.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description; // Optional field

    @NotBlank(message = "Topic is required")
    private String topic;

    @NotBlank(message = "Difficulty is required")
    private String difficulty;

    @NotNull(message = "Solved status must be provided")
    private Boolean isSolved; // Use Boolean for validation


}
