package br.com.palm.devshowcase.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.devshowcase.dto.ProfileDetailResponseDTO;
import br.com.palm.devshowcase.dto.ProfileRequestDTO;
import br.com.palm.devshowcase.dto.ProfileResponseDTO;
import br.com.palm.devshowcase.exception.BusinessException;
import br.com.palm.devshowcase.exception.ResourceNotFoundException;
import br.com.palm.devshowcase.model.Profile;
import br.com.palm.devshowcase.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    @Transactional(readOnly = true)
    public List<ProfileResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(ProfileResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProfileDetailResponseDTO buscarPorId(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("perfil não encontrado com o id: " + id));
        return ProfileDetailResponseDTO.fromEntity(profile);
    }

    @Transactional
    public ProfileResponseDTO cadastrar(ProfileRequestDTO dto) {
        if (repository.existsByEmailIgnoreCase(dto.email().trim())) {
            throw new BusinessException("já existe um perfil cadastrado com o email: " + dto.email());
        }

        Profile profile = new Profile(
            dto.name().trim(),
            dto.email().trim().toLowerCase(),
            dto.bio(),
            dto.avatarUrl(),
            dto.githubUrl(),
            dto.linkedinUrl()
        );

        Profile salvo = repository.save(profile);
        return ProfileResponseDTO.fromEntity(salvo);
    }
}
