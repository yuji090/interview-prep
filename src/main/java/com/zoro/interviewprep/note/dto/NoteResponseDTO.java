// NoteResponseDTO.java
package com.zoro.interviewprep.note.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponseDTO {
    private Long id;
    private String content;
    private Long questionId;
}
