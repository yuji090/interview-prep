package com.zoro.interviewprep.question;

import com.zoro.interviewprep.question.dto.QuestionRequestDTO;
import com.zoro.interviewprep.question.dto.QuestionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<QuestionResponseDTO> add(@Valid @RequestBody QuestionRequestDTO dto) {
        return ResponseEntity.ok(questionService.addQuestion(dto));
    }

    @GetMapping("/user")
    public ResponseEntity<List<QuestionResponseDTO>> getUserQuestions(@RequestParam String email) {
        return ResponseEntity.ok(questionService.getUserQuestions(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/topic")
    public ResponseEntity<List<QuestionResponseDTO>> byTopic(@RequestParam String topic) {
        return ResponseEntity.ok(questionService.filterByTopic(topic));
    }

    @GetMapping("/filter/solved")
    public ResponseEntity<List<QuestionResponseDTO>> bySolved(@RequestParam boolean solved) {
        return ResponseEntity.ok(questionService.filterBySolved(solved));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody QuestionRequestDTO dto) {
        return ResponseEntity.ok(questionService.updateQuestion(id, dto));
    }


    // ✅ SRP: Routes only for Question CRUD and filtering
    // ✅ DIP: Only depends on service and DTOs, not models
}
