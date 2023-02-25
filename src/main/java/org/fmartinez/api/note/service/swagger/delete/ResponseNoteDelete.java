package org.fmartinez.api.note.service.swagger.delete;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.fmartinez.api.note.service.entity.Note;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@Operation(summary = "Delete Note")
@ApiResponses({
        @ApiResponse(responseCode = "204", description = "Delete an existing Note by id", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Note.class))
        }),
        @ApiResponse(responseCode = "404", description = "Note not found")
})
public @interface ResponseNoteDelete {
}
