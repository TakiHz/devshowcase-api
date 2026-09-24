package br.com.palm.devshowcase.dto;

import br.com.palm.devshowcase.model.Technology;

public record TechnologyResponseDTO(Long id, String name) {

    public static TechnologyResponseDTO fromEntity(Technology tech) {
        if (tech == null) return null;
        return new TechnologyResponseDTO(tech.getId(), tech.getName());
    }
}