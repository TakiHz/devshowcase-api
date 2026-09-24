package br.com.palm.devshowcase.dto;

import jakarta.validation.constraints.NotNull;

public record ProjectTechnologyRequestDTO(
    @NotNull(message = "o id da tecnologia é obrigatório")
    Long technologyId
) {}
