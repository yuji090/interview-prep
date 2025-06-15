package com.zoro.interviewprep.note;

import com.zoro.interviewprep.note.dto.NoteRequestDTO;
import com.zoro.interviewprep.note.dto.NoteResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<NoteResponseDTO> addNote(@Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.ok(noteService.addNote(dto));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<NoteResponseDTO>> getNotes(@PathVariable Long questionId) {
        return ResponseEntity.ok(noteService.getNotesForQuestion(questionId));
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<NoteResponseDTO> updateNote(
            @PathVariable Long noteId,
            @Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.ok(noteService.updateNote(noteId, dto));
    }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Clean: Uses DTOs for both input and output
}
