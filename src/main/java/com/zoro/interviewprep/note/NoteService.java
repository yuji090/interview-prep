package com.zoro.interviewprep.note;

import com.zoro.interviewprep.note.dto.NoteRequestDTO;
import com.zoro.interviewprep.note.dto.NoteResponseDTO;
import com.zoro.interviewprep.question.Question;
import com.zoro.interviewprep.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepo;
    private final QuestionRepository questionRepo;

    public NoteResponseDTO addNote(NoteRequestDTO dto) {
        Question question = questionRepo.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

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

        return question.getNotes().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private NoteResponseDTO toResponseDTO(Note note) {
        return NoteResponseDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .questionId(note.getQuestion().getId())
                .build();
    }

    public NoteResponseDTO updateNote(Long noteId, NoteRequestDTO dto) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setContent(dto.getContent());
        Note updated = noteRepo.save(note);

        return toResponseDTO(updated);
    }

    public void deleteNote(Long noteId) {
        if (!noteRepo.existsById(noteId)) {
            throw new RuntimeException("Note not found");
        }
        noteRepo.deleteById(noteId);
    }

}
