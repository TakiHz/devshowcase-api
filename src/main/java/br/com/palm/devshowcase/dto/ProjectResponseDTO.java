package br.com.palm.devshowcase.dto;

import java.time.LocalDateTime;
import java.util.List;
import br.com.palm.devshowcase.model.Project;

public record ProjectResponseDTO(
    Long id,
    String title,
    String description,
    String repositoryUrl,
    String liveDemoUrl,
    Integer likes,
    Double averageRating,
    LocalDateTime createdAt,
    Long profileId,
    String profileName,
    List<TechnologyResponseDTO> technologies
) {
    public static ProjectResponseDTO fromEntity(Project project) {
        if (project == null) return null;
        List<TechnologyResponseDTO> techDtos = project.getTechnologies() != null
            ? project.getTechnologies().stream().map(TechnologyResponseDTO::fromEntity).toList()
            : List.of();

        return new ProjectResponseDTO(
            project.getId(),
            project.getTitle(),
            project.getDescription(),
            project.getRepositoryUrl(),
            project.getLiveDemoUrl(),
            project.getLikes(),
            project.getAverageRating(),
            project.getCreatedAt(),
            project.getProfile() != null ? project.getProfile().getId() : null,
            project.getProfile() != null ? project.getProfile().getName() : null,
            techDtos
        );
    }
}
