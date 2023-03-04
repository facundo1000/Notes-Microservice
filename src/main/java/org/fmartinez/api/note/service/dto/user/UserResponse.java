package org.fmartinez.api.note.service.dto.user;

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
}
