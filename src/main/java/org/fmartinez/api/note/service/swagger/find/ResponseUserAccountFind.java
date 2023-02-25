package org.fmartinez.api.note.service.swagger.find;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

@Operation(summary = "Get User-Account")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Found User-Account", content = {
                @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", description = "Characters not found")
})

public @interface ResponseUserAccountFind {
}
