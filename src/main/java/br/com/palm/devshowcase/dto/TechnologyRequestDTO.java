package br.com.palm.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TechnologyRequestDTO(
    @NotBlank(message = "o nome da tecnologia é obrigatório")
    @Size(max = 50, message = "o nome deve ter no máximo 50 caracteres")
    String name
) {}
