package org.fmartinez.api.note.service.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestNote {
    @NotBlank(message = " field title must not be null")
    @JsonProperty(required = true)
    private String title;
    @NotBlank(message = "field note must not be null")
    @JsonProperty(required = true)
    private String note;

}
