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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.fmartinez.api.note.service.constant.ApplicationConstant.NOTE_PATH;

@Tag(name = "Note Controller", description = "Note side operations")
@RequestMapping(NOTE_PATH)
public interface NoteController {

    @Operation(summary = "Get Note List")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get list of all notes", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Note.class)))
            }),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/list")
    ResponseEntity<List<Note>> findAll();


    @Operation(summary = "Get Note")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get note by id", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Note.class))
            }),
            @ApiResponse(responseCode = "404", description = "Note not found")
    })
    @GetMapping("/id/{id}")
    ResponseEntity<Note> findById(@PathVariable String id);


    @Operation(summary = "Create Note")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create note", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Note.class))
            }),
            @ApiResponse(responseCode = "404", description = "Note not found"),
            @ApiResponse(responseCode = "500", description = "Bad request")
    })
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody @Valid Note note, BindingResult result);


    @Operation(summary = "Update Note")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Update note by id", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Note.class))
            }),
            @ApiResponse(responseCode = "404", description = "Note not found"),
            @ApiResponse(responseCode = "500", description = "Bad request")
    })
    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@RequestBody @Valid Note note, BindingResult result, @PathVariable String id);

    @DeleteMapping("/delete/{id}")
    @ResponseNoteDelete
    ResponseEntity<Void> delete(@PathVariable String id);
}
