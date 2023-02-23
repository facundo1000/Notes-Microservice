package org.fmartinez.api.note.service.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("user")
public class UserAccount {
    private String id;
    @NotBlank(message = "username cannot be empty")
    private String username;
    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;
    @DBRef
    private List<Note> note;

    public void addNote(Note note) {
        this.note.add(note);
        note.setUser(this.getEmail());
    }

    public void removeNote(Note note) {
        this.note.remove(note);
        note.setUser(null);
    }

}
