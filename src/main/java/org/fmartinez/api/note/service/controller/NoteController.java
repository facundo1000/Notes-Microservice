package org.fmartinez.api.note.service.controller;

import jakarta.validation.Valid;
import org.fmartinez.api.note.service.dto.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/mongo")
public interface NoteController {

    @GetMapping("/list")
    ResponseEntity<List<Note>> findAll();

    @GetMapping("/id/{id}")
    ResponseEntity<Note> findById(@PathVariable String id);

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody @Valid Note note, BindingResult result);

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody @Valid Note note, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
