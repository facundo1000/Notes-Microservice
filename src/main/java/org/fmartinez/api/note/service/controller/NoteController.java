package org.fmartinez.api.note.service.controller;

import jakarta.validation.Valid;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.swagger.delete.ResponseNoteDelete;
import org.fmartinez.api.note.service.swagger.find.ResponseFindNote;
import org.fmartinez.api.note.service.swagger.save.ResponseNoteSave;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/mongo")
public interface NoteController {

    @GetMapping("/list")
    @ResponseFindNote
    ResponseEntity<List<Note>> findAll();

    @GetMapping("/id/{id}")
    @ResponseFindNote
    ResponseEntity<Note> findById(@PathVariable String id);

    @PostMapping("/create")
    @ResponseNoteSave
    ResponseEntity<?> create(@RequestBody @Valid Note note, BindingResult result);

    @PutMapping("/update/{id}")
    @ResponseNoteSave
    ResponseEntity<?> update(@RequestBody @Valid Note note, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    @ResponseNoteDelete
    ResponseEntity<Void> delete(@PathVariable String id);
}
