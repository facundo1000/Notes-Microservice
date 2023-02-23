package org.fmartinez.api.note.service.dto;

import lombok.Builder;

@Builder
public record UserResponse(String user, String email) {
}
