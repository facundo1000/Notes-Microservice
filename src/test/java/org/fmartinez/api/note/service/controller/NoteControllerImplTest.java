package org.fmartinez.api.note.service.controller;

import org.fmartinez.api.note.service.controller.impl.NoteControllerImpl;
import org.fmartinez.api.note.service.dto.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.service.NoteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteModel;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteControllerImplTest {

    @Mock
    NoteService service;

    @InjectMocks
    NoteControllerImpl controller;

    private static Note note;
    private static ResponseNote responseNote;

    private static BindingResult result;

    @BeforeAll
    static void setUp() {
        note = buildNoteModel();
        responseNote = buildNoteResponse();
        result = new BeanPropertyBindingResult(note, "note");
    }

    @Test
    @DisplayName("Test: get all notes [200]")
    void getAllNotes() {
        when(service.findAll()).thenReturn(List.of(new Note()));
        ResponseEntity<List<Note>> response = controller.findAll();

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(List.class);

        verify(service).findAll();
    }

    @Test
    @DisplayName("Test: get note by id response [200]")
    void getNoteById() {
        when(service.getById(anyString())).thenReturn(note);
        ResponseEntity<Note> response = controller.findById("64f36t0f359e323cb8b8fb35");

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isInstanceOf(Note.class);

        verify(service).getById(anyString());
    }

    @Test
    @DisplayName("Test: new note response wildcard [201]")
    void createNote() {
        when(service.create(any(Note.class))).thenReturn(note);

        ResponseEntity<?> response = controller.create(note, result);

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(result.hasErrors()).isFalse();
        assertThat(result.getFieldErrors()).isEmpty();

        verify(service).create(any(Note.class));
    }

    @Test
    @DisplayName("Test: get updated note response [200]")
    void updateNote() {
        when(service.update(any(Note.class), anyString())).thenReturn(note);
        ResponseEntity<?> response = controller.update(note, result, "64f36t0f359e323cb8b8fb35");

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(result.hasErrors()).isFalse();
        assertThat(result.getFieldErrors()).isEmpty();

        verify(service).update(any(Note.class), anyString());
    }

    @Test
    @DisplayName("Test: delete note by id response [204]")
    void deleteNote() {

        ResponseEntity<Void> response = controller.delete("64f36t0f359e323cb8b8fb35");

        assertThat(response).isNotNull();
        assertThat(response).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        verify(service).deleteById(anyString());
    }
}
