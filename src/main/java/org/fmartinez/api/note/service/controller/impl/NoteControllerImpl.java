package org.fmartinez.api.note.service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.controller.NoteController;
import org.fmartinez.api.note.service.dto.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NoteControllerImpl implements NoteController {
    private final NoteService service;

    @Override
    public ResponseEntity<List<Note>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Note> findById(String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<?> create(Note note, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        ResponseNote dto;

        if (!result.hasErrors()) {
            service.create(note);
            dto = new ResponseNote(note.getTitle(), note.getCreated());
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        result.getFieldErrors()
                .forEach(fieldError -> response.put("error", fieldError.getDefaultMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> update(Note note, BindingResult result, String id) {
        Map<String, Object> response = new HashMap<>();
        ResponseNote dto;

        if (!result.hasErrors()) {
            service.update(note, id);
            dto = new ResponseNote(note.getTitle(), note.getUpdated());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        result.getFieldErrors()
                .forEach(fieldError -> response.put("error", fieldError.getDefaultMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
