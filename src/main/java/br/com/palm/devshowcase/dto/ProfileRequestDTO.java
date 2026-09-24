package br.com.palm.devshowcase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfileRequestDTO(
    @NotBlank(message = "o nome é obrigatório")
    @Size(max = 100, message = "o nome deve ter no máximo 100 caracteres")
    String name,

    @NotBlank(message = "o email é obrigatório")
    @Email(message = "o email informado é inválido")
    String email,

    String bio,
    String avatarUrl,
    String githubUrl,
    String linkedinUrl
) {}
