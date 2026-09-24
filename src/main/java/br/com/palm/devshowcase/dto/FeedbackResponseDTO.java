package br.com.palm.devshowcase.dto;

import java.time.LocalDateTime;
import br.com.palm.devshowcase.model.Feedback;

public record FeedbackResponseDTO(
    Long id,
    String author,
    String comment,
    Integer rating,
    LocalDateTime createdAt,
    Long projectId
) {
    public static FeedbackResponseDTO fromEntity(Feedback feedback) {
        if (feedback == null) return null;
        return new FeedbackResponseDTO(
            feedback.getId(),
            feedback.getAuthor(),
            feedback.getComment(),
            feedback.getRating(),
            feedback.getCreatedAt(),
            feedback.getProject() != null ? feedback.getProject().getId() : null
        );
    }
}
