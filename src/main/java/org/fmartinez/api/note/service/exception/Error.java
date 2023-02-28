package org.fmartinez.api.note.service.exception;

import lombok.Builder;

@Builder
public record Error(String message, String code) {
}
