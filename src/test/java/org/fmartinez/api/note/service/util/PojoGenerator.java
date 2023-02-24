package org.fmartinez.api.note.service.util;

import org.fmartinez.api.note.service.dto.ResponseNote;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class PojoGenerator {

    private static final Clock NOW = Clock
            .fixed(Instant.parse("2023-02-22T14:30:00.00Z"),
                    ZoneId.of("GMT"));

    public static Note buildNoteModel() {
        return Note.builder()
                .id("64f36t0f359e323cb8b8fb35")
                .user("Arnold")
                .title("Body Building")
                .note("I'll be back")
                .updated(LocalDateTime.now(NOW))
                .created(LocalDateTime.now(NOW))
                .build();
    }

    public static ResponseNote buildNoteResponse() {
        return ResponseNote.builder()
                .title("Title")
                .updated(LocalDateTime.now())
                .build();
    }

    public static UserAccount buildUserAccount() {
        return UserAccount.builder()
                .id("64f36t0f956e323cb9b9fp35")
                .username("Leonardo")
                .email("davinci@monalisa.com")
                .password("iluminati")
                .note(new ArrayList<>())
                .build();
    }

}
