package org.fmartinez.api.note.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.fmartinez.api.note.service.dto.note.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.swagger.delete.ResponseNoteDelete;
import org.fmartinez.api.note.service.swagger.find.ResponseFindAllNotes;
import org.fmartinez.api.note.service.swagger.find.ResponseFindByIdNote;
import org.fmartinez.api.note.service.swagger.save.ResponseNoteSave;
import org.fmartinez.api.note.service.swagger.save.ResponseNoteUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.fmartinez.api.note.service.constant.ApplicationConstant.NOTE_PATH;

@Tag(name = "Note Controller", description = "Note side operations")
@RequestMapping(NOTE_PATH)
public interface NoteController {


    @ResponseFindAllNotes
    @GetMapping("/list")
    ResponseEntity<List<Note>> findAll();


    @ResponseFindByIdNote
    @GetMapping("/id/{id}")
    ResponseEntity<Note> findById(@PathVariable String id);


    @ResponseNoteSave
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody @Valid Note note, BindingResult result);

    @ResponseNoteUpdate
    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody @Valid Note note, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    @ResponseNoteDelete
    ResponseEntity<Void> delete(@PathVariable String id);
}
