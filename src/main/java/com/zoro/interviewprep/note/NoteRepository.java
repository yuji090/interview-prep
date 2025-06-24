package com.zoro.interviewprep.note;

import com.zoro.interviewprep.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByQuestion(Question question);
}
