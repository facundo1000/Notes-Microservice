package org.fmartinez.api.note.service.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fmartinez.api.note.service.entity.Note;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse extends UserRequest {
    private List<Note> note;

    public UserResponse(@NotBlank(message = "username is required") String username,
                        @NotBlank(message = "email is required") String email,
                        @NotBlank(message = "password is required") String password,
                        List<Note> note) {
        super(username, email, password);
        this.note = note;
    }
}
