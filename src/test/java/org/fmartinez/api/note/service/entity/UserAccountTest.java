package org.fmartinez.api.note.service.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildNoteModel;
import static org.fmartinez.api.note.service.util.PojoGenerator.buildUserAccount;

public class UserAccountTest {

    private static Note note;
    private static UserAccount userAccount;


    @BeforeAll
    static void setUp() {
        note = buildNoteModel();
        userAccount = buildUserAccount();
    }


    @Test
    @DisplayName("Test: adding note function")
    void addNoteToUser() {
        userAccount.addNote(note);

        assertThat(userAccount.getNote()).isNotEmpty();
        assertThat(userAccount.getNote()).hasSize(userAccount.getNote().size());
        assertThat(userAccount.getNote()).isInstanceOf(List.class);

        assertThat(note.getUser()).isEqualTo(userAccount.getEmail());
    }

    @Test
    @DisplayName("Test: remove note function")
    void removeNoteFromUser() {
        userAccount.removeNote(note);

        assertThat(userAccount.getNote()).isEmpty();
        assertThat(note.getUser()).isNull();


    }
}
