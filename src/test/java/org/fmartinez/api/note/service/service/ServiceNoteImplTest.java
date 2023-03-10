package org.fmartinez.api.note.service.service;

import org.fmartinez.api.note.service.dto.note.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.mapper.MapStructMapper;
import org.fmartinez.api.note.service.repository.NotesRepository;
import org.fmartinez.api.note.service.service.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteModel;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceNoteImplTest {
    @Mock
    NotesRepository repository;

    @Mock
    MapStructMapper mapper;

    @InjectMocks
    NoteServiceImpl service;

    private static Note note;
    private static ResponseNote responseNote;

    private static final Clock CLOCK = Clock
            .fixed(Instant.parse("2023-02-22T14:30:00.00Z"), ZoneId.of("GMT"));

    @BeforeAll
    static void setUp() {
        note = buildNoteModel();
        responseNote = buildNoteResponse();
    }


    @Test
    @DisplayName("Test: get all notes")
    void getAllNotes() {
        when(repository.findAll()).thenReturn(List.of(note));
        List<Note> list = service.findAll();

        assertThat(list).isNotNull().isInstanceOf(List.class);
        assertThat(list).isNotEmpty();
        assertThat(list).hasSize(list.size());
        assertThat(list.iterator().next()).isInstanceOf(Note.class);

        verify(repository).findAll();
    }


    @Test
    @DisplayName("Test: get a note by id")
    void getNoteById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(note));
        Note noteTest = service.getById("64f36t0f359e323cb8b8fb35");

        assertThat(noteTest).isNotNull();
        assertThat(noteTest).isInstanceOf(Note.class);
        assertThat(noteTest.getId()).isEqualTo("64f36t0f359e323cb8b8fb35");

        verify(repository).findById(anyString());
    }

    @Test
    @DisplayName("Test: create new note")
    void createNote() {
        when(repository.save(any(Note.class))).thenReturn(note);
        when(mapper.noteToResponseNote(any(Note.class))).thenReturn(responseNote);

        LocalDateTime timeNow = LocalDateTime.now(CLOCK);
        ResponseNote noteTest = service.create(note);

        assertThat(noteTest).isNotNull();
        assertThat(noteTest.getNote()).isNotEmpty();
        assertThat(noteTest.getTitle()).isNotEmpty();
        assertThat(noteTest.getCreated()).isEqualTo(timeNow);
        assertThat(noteTest.getUpdated()).isEqualTo(timeNow);

        verify(repository).save(any(Note.class));
        verify(mapper).noteToResponseNote(any(Note.class));
    }

    @Test
    @DisplayName("Test: update a note by ID")
    void updateNote() {
        when(repository.save(any(Note.class))).thenReturn(note);
        when(repository.findById(anyString())).thenReturn(Optional.of(note));
        LocalDateTime timeNow = LocalDateTime.now(CLOCK);
        Note noteTest = service.update(note, "64f36t0f359e323cb8b8fb35");

        assertThat(noteTest).isNotNull().isInstanceOf(Note.class);
        assertThat(noteTest.getNote()).isNotEmpty();
        assertThat(noteTest.getUser()).isNotEmpty();
        assertThat(noteTest.getCreated()).isEqualTo(timeNow);
        assertThat(noteTest.getUpdated()).isEqualTo(timeNow);

        verify(repository).save(any(Note.class));
        verify(repository).findById(anyString());
    }

    @Test
    @DisplayName("Test: delete note by ID")
    void deleteNote() {
        Void delete = service.deleteById("64f36t0f359e323cb8b8fb35");
        assertThat(delete).isNull();
        verify(repository).deleteById(anyString());
    }


}
