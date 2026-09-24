package br.com.palm.devshowcase.dto;

import br.com.palm.devshowcase.model.Profile;

public record ProfileResponseDTO(
    Long id,
    String name,
    String email,
    String bio,
    String avatarUrl,
    String githubUrl,
    String linkedinUrl
) {
    public static ProfileResponseDTO fromEntity(Profile profile) {
        if (profile == null) return null;
        return new ProfileResponseDTO(
            profile.getId(),
            profile.getName(),
            profile.getEmail(),
            profile.getBio(),
            profile.getAvatarUrl(),
            profile.getGithubUrl(),
            profile.getLinkedinUrl()
        );
    }
}
