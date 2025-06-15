package com.zoro.interviewprep.question;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zoro.interviewprep.note.Note;
import com.zoro.interviewprep.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100)
    private String title;

    private String description;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String difficulty; // Easy / Medium / Hard

    @Column(nullable = false)
    private Boolean isSolved;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Note> notes; // ✅ All notes linked to this question

    // ✅ SRP: Represents only Question data
    // ✅ OCP: Easily extended with Note relationship
}
