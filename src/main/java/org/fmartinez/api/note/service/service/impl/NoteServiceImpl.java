package org.fmartinez.api.note.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.repository.NotesRepository;
import org.fmartinez.api.note.service.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NotesRepository repository;

    @Override
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Override
    public Note getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("id don't found in the system"));
    }

    @Override
    public Note create(Note note) {
        return repository.save(note);
    }

    @Override
    public Note update(Note note, String id) {

        if (id != null) {
            Note update = repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("id don't found in the system"));
            update.setUser(note.getUser());
            update.setTitle(note.getTitle());
            update.setNote(note.getNote());
            update.setUpdated(note.getUpdated());
            return repository.save(update);
        }
        throw new NoSuchElementException("id don't found in the system");
    }

    @Override
    public Void deleteById(String id) {
        if (id != null) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException("id don't found in the system");
        }
        return null;
    }
}
