package com.zoro.interviewprep.note;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zoro.interviewprep.question.Question;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonBackReference
    private Question question;

    // ✅ SRP: Only handles note data
    // ✅ DIP: Uses Question abstraction, not raw ID
}
