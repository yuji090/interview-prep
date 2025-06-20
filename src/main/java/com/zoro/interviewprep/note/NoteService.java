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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepo;
    private final QuestionRepository questionRepo;
    private final UserUtil userUtil; // ✅ Injecting for current user check

    public NoteResponseDTO addNote(NoteRequestDTO dto) {
        Question question = questionRepo.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // 🔒 Optional: check if current user owns this question
        if (!question.getUser().getId().equals(userUtil.getCurrentUser().getId())) {
            throw new AccessDeniedException("You are not authorized to add a note to this question");
        }

        Note note = Note.builder()
                .content(dto.getContent())
                .question(question)
                .build();

        Note savedNote = noteRepo.save(note);
        return toResponseDTO(savedNote);
    }

    public List<NoteResponseDTO> getNotesForQuestion(Long questionId) {
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // ✅ Check if the logged-in user is the owner of the question
        User currentUser = userUtil.getCurrentUser();
        if (!question.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You are not authorized to view notes for this question");
        }

        return question.getNotes().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public NoteResponseDTO updateNote(Long noteId, NoteRequestDTO dto) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // ✅ Prevent update if user doesn't own the question
        if (!note.getQuestion().getUser().getId().equals(userUtil.getCurrentUser().getId())) {
            throw new AccessDeniedException("You are not authorized to update this note");
        }

        note.setContent(dto.getContent());
        Note updated = noteRepo.save(note);

        return toResponseDTO(updated);
    }

    public void deleteNote(Long noteId) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // ✅ Prevent delete if user doesn't own the question
        if (!note.getQuestion().getUser().getId().equals(userUtil.getCurrentUser().getId())) {
            throw new AccessDeniedException("You are not authorized to delete this note");
        }

        noteRepo.delete(note);
    }

    private NoteResponseDTO toResponseDTO(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .questionId(note.getQuestion().getId())
                .build();
    }
}
