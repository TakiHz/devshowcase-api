package br.com.palm.devshowcase.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProjectRequestDTO(
    @NotBlank(message = "o título é obrigatório")
    @Size(max = 150, message = "o título deve ter no máximo 150 caracteres")
    String title,

    @NotBlank(message = "a descrição é obrigatória")
    String description,

    @NotBlank(message = "a url do repositório é obrigatória")
    String repositoryUrl,

    String liveDemoUrl,

    @NotNull(message = "o id do perfil é obrigatório")
    Long profileId,

    List<Long> technologyIds
) {}
