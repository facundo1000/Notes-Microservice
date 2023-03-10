package org.fmartinez.api.note.service.swagger.find;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.fmartinez.api.note.service.entity.Note;
import org.fmartinez.api.note.service.entity.UserAccount;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@Operation(summary = "Get Note List")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Get list of all notes", content = {
                @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Note.class)))
        }),
        @ApiResponse(responseCode = "404", description = "Note not found")
})
public @interface ResponseFindAllNotes {
}
