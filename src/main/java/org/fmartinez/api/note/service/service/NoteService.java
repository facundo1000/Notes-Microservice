package org.fmartinez.api.note.service.service;

import org.fmartinez.api.note.service.entity.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll();

    Note getById(String id);

    Note create(Note note);

    Note update(Note note, String id);

    Void deleteById(String id);
}
