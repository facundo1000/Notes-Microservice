package org.fmartinez.api.note.service.mapper;

import org.fmartinez.api.note.service.dto.note.ResponseNote;
import org.fmartinez.api.note.service.dto.user.UserResponse;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    UserResponse mapToUserResponse(UserAccount user);
    ResponseNote noteToResponseNote(Note note);
}
