package org.fmartinez.api.note.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseNote(String title,
                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updated) {
}
