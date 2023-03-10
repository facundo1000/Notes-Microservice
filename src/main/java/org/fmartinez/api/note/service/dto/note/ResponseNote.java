package org.fmartinez.api.note.service.dto.note;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseNote extends RequestNote {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    public ResponseNote(@NotBlank(message = "title must not be null")
                        String title,
                        @NotBlank(message = "note must not be null") String note,
                        LocalDateTime updated,
                        LocalDateTime created) {
        super(title, note);
        this.updated = updated;
        this.created = created;
    }
}
