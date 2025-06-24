package com.zoro.interviewprep.question;

import com.zoro.interviewprep.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByUser(User user);
    List<Question> findByUserAndTopic(User user, String topic);
    List<Question> findByUserAndIsSolved(User user, boolean isSolved);

    @Query("SELECT q FROM Question q WHERE q.user = :user "
            + "AND (:topic IS NULL OR q.topic = :topic) "
            + "AND (:difficulty IS NULL OR q.difficulty = :difficulty) "
            + "AND (:isSolved IS NULL OR q.isSolved = :isSolved)")
    List<Question> filterByParams(@Param("user") User user,
                                  @Param("topic") String topic,
                                  @Param("difficulty") String difficulty,
                                  @Param("isSolved") Boolean isSolved);



    // ✅ SRP: Only database queries for Question
}
