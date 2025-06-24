package com.zoro.interviewprep.note;

import com.zoro.interviewprep.note.dto.NoteRequestDTO;
import com.zoro.interviewprep.note.dto.NoteResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<NoteResponseDTO> addOrUpdateNote(@Valid @RequestBody NoteRequestDTO dto) {
        return ResponseEntity.ok(noteService.addOrUpdateNote(dto));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<NoteResponseDTO> getNote(@PathVariable Long questionId) {
        return ResponseEntity.ok(noteService.getNoteForQuestion(questionId));
    }


    //Removed DELETE and PUT — not needed in one-note-per-question logic
}
