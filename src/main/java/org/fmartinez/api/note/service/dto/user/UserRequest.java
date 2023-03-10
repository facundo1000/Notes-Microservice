package org.fmartinez.api.note.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    @NotBlank(message = "field username is required")
    @JsonProperty(required = true)
    private String username;
    @NotBlank(message = "field email is required")
    @JsonProperty(required = true)
    private String email;
    @NotBlank(message = "field password is required")
    @JsonProperty(required = true)
    private String password;
}
