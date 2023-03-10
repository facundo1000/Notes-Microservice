package org.fmartinez.api.note.service.controller.impl;

import lombok.RequiredArgsConstructor;
import org.fmartinez.api.note.service.controller.NoteController;
import org.fmartinez.api.note.service.dto.note.ResponseNote;
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

        if (!result.hasErrors()) {
            return new ResponseEntity<>(service.create(note), HttpStatus.CREATED);
        }
        result.getFieldErrors()
                .forEach(fieldError -> response.put("error", fieldError.getDefaultMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> update(Note note, BindingResult result, String id) {
        Map<String, Object> response = new HashMap<>();

        if (!result.hasErrors()) {
            return new ResponseEntity<>(service.update(note, id), HttpStatus.OK);
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
