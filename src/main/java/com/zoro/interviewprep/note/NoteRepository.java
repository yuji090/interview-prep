package com.zoro.interviewprep.note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // ✅ ISP: Only Note-specific DB methods
}
