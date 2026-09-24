package br.com.palm.devshowcase.exception;

import java.time.Instant;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseDTO(
    Instant timestamp,
    Integer status,
    String error,
    String message,
    String path,
    Map<String, String> fields
) {
    public ErrorResponseDTO(Instant timestamp, Integer status, String error, String message, String path) {
        this(timestamp, status, error, message, path, null);
    }
}
