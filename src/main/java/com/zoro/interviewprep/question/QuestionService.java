package com.zoro.interviewprep.question;

import com.zoro.interviewprep.question.dto.QuestionRequestDTO;
import com.zoro.interviewprep.question.dto.QuestionResponseDTO;
import com.zoro.interviewprep.user.User;
import com.zoro.interviewprep.user.UserRepository;
import com.zoro.interviewprep.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepo;
    private final UserRepository userRepo;
    private final UserUtil userUtil;

    public QuestionResponseDTO addQuestion(QuestionRequestDTO dto) {
        User user = userUtil.getCurrentUser(); // ✅ Secure + JWT-based

        Question question = Question.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .topic(dto.getTopic())
                .difficulty(dto.getDifficulty())
                .isSolved(dto.getIsSolved())
                .createdAt(LocalDate.now())
                .user(user)
                .build();

        Question saved = questionRepo.save(question);

        return toResponseDTO(saved);
    }

    public List<QuestionResponseDTO> getUserQuestions() {
        User user = userUtil.getCurrentUser(); // ✅ Logged-in user from JWT

        return questionRepo.findByUser(user)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


    public void deleteQuestion(Long id) {
        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // ✅ Authorization check
        if (!question.getUser().getId().equals(userUtil.getCurrentUser().getId())) {
            throw new RuntimeException("Unauthorized to delete this question");
        }

        questionRepo.delete(question);
    }

    public List<QuestionResponseDTO> filterByTopic(String topic) {
        User user = userUtil.getCurrentUser();
        return questionRepo.findByUserAndTopic(user, topic)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<QuestionResponseDTO> filterBySolved(boolean isSolved) {
        User user = userUtil.getCurrentUser();
        return questionRepo.findByUserAndIsSolved(user, isSolved)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private QuestionResponseDTO toResponseDTO(Question q) {
        return QuestionResponseDTO.builder()
                .id(q.getId())
                .title(q.getTitle())
                .description(q.getDescription())
                .topic(q.getTopic())
                .difficulty(q.getDifficulty())
                .isSolved(q.getIsSolved())
                .createdAt(q.getCreatedAt())
                .userEmail(q.getUser().getEmail())
                .build();
    }

    public QuestionResponseDTO updateQuestion(Long id, QuestionRequestDTO dto) {
        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // ✅ Authorization check
        if (!question.getUser().getId().equals(userUtil.getCurrentUser().getId())) {
            throw new RuntimeException("Unauthorized to update this question");
        }

        // Only update fields from DTO
        question.setTitle(dto.getTitle());
        question.setDescription(dto.getDescription());
        question.setTopic(dto.getTopic());
        question.setDifficulty(dto.getDifficulty());
        question.setIsSolved(dto.getIsSolved());

        Question updated = questionRepo.save(question);
        return toResponseDTO(updated);
    }


    // ✅ SRP: Each method does one task
    // ✅ OCP: New logic (like note support) can be added without changing existing methods
    // ✅ DIP: Depends on UserUtil abstraction to fetch user, not raw email input
}
