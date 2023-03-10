package org.fmartinez.api.note.service.swagger.save;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.fmartinez.api.note.service.entity.UserAccount;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@Operation(summary = "Add Note to User-Account")
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Create User-Account", content = {
                @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserAccount.class))
        }),
        @ApiResponse(responseCode = "404", description = "Resource not found", content = {
                @Content(mediaType = "application/json")
        })
})
public @interface ResponseUserAddNote {
}
