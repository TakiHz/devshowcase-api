package br.com.palm.devshowcase.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequestDTO(
    @NotBlank(message = "o nome do autor é obrigatório")
    @Size(max = 100, message = "o nome do autor deve ter no máximo 100 caracteres")
    String author,

    @NotBlank(message = "o comentário é obrigatório")
    String comment,

    @NotNull(message = "a nota de avaliação é obrigatória")
    @Min(value = 1, message = "a nota mínima é 1")
    @Max(value = 5, message = "a nota máxima é 5")
    Integer rating
) {}
