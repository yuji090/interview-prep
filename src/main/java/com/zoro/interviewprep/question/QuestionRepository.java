package com.zoro.interviewprep.question;

import com.zoro.interviewprep.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByUser(User user);
    List<Question> findByTopic(String topic);
    List<Question> findByIsSolved(boolean isSolved);

    // ✅ SRP: Only database queries for Question
}
