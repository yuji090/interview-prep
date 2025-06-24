package com.zoro.interviewprep.note;

import com.zoro.interviewprep.note.dto.NoteRequestDTO;
import com.zoro.interviewprep.note.dto.NoteResponseDTO;
import com.zoro.interviewprep.question.Question;
import com.zoro.interviewprep.question.QuestionRepository;
import com.zoro.interviewprep.user.User;
import com.zoro.interviewprep.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepo;
    private final QuestionRepository questionRepo;
    private final UserUtil userUtil;

    public NoteResponseDTO addOrUpdateNote(NoteRequestDTO dto) {
        User currentUser = userUtil.getCurrentUser();
        Question question = questionRepo.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // 🔐 Authorization: only question owner can add/update note
        if (!question.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to add or update note for this question");
        }

        Optional<Note> existingNoteOpt = noteRepo.findByQuestion(question);

        if (existingNoteOpt.isPresent()) {
            Note note = existingNoteOpt.get();
            note.setContent(dto.getContent().trim());
            return toResponseDTO(noteRepo.save(note));
        } else {
            Note note = Note.builder()
                    .content(dto.getContent().trim())
                    .question(question)
                    .build();
            return toResponseDTO(noteRepo.save(note));
        }
    }

    public NoteResponseDTO getNoteForQuestion(Long questionId) {
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        User currentUser = userUtil.getCurrentUser();
        if (!question.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Unauthorized");
        }

        return question.getNotes().isEmpty()
                ? null
                : toResponseDTO(question.getNotes().get(0)); // or use Optional
    }


    private NoteResponseDTO toResponseDTO(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .questionId(note.getQuestion().getId())
                .build();
    }
}
