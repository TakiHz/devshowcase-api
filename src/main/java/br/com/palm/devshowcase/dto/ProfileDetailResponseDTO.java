package br.com.palm.devshowcase.dto;

import java.util.List;
import br.com.palm.devshowcase.model.Profile;

public record ProfileDetailResponseDTO(
    Long id,
    String name,
    String email,
    String bio,
    String avatarUrl,
    String githubUrl,
    String linkedinUrl,
    List<ProjectResponseDTO> projects
) {
    public static ProfileDetailResponseDTO fromEntity(Profile profile) {
        if (profile == null) return null;
        List<ProjectResponseDTO> projectDtos = profile.getProjects() != null
            ? profile.getProjects().stream().map(ProjectResponseDTO::fromEntity).toList()
            : List.of();

        return new ProfileDetailResponseDTO(
            profile.getId(),
            profile.getName(),
            profile.getEmail(),
            profile.getBio(),
            profile.getAvatarUrl(),
            profile.getGithubUrl(),
            profile.getLinkedinUrl(),
            projectDtos
        );
    }
}
