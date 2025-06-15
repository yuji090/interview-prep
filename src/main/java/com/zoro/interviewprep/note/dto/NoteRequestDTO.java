// NoteRequestDTO.java
package com.zoro.interviewprep.note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequestDTO {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotBlank(message = "Note content is required")
    private String content;
}
