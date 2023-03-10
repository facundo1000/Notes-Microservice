package org.fmartinez.api.note.service.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

import static org.fmartinez.api.note.service.constant.ApplicationConstant.EMAIL_REGEX;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("user")
public class UserAccount {
    @MongoId
    private String id;
    @NotBlank(message = "username cannot be empty")
    private String username;
    @NotBlank(message = "email cannot be empty")
    @Email(regexp = EMAIL_REGEX)
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
