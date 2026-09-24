package br.com.palm.devshowcase.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.devshowcase.dto.TechnologyRequestDTO;
import br.com.palm.devshowcase.dto.TechnologyResponseDTO;
import br.com.palm.devshowcase.exception.BusinessException;
import br.com.palm.devshowcase.model.Technology;
import br.com.palm.devshowcase.repository.TechnologyRepository;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository repository;

    @Transactional(readOnly = true)
    public List<TechnologyResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(TechnologyResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public TechnologyResponseDTO cadastrar(TechnologyRequestDTO dto) {
        if (repository.existsByNameIgnoreCase(dto.name().trim())) {
            throw new BusinessException("já existe uma tecnologia cadastrada com o nome: " + dto.name());
        }

        Technology tech = new Technology(dto.name().trim());
        Technology salvo = repository.save(tech);
        return TechnologyResponseDTO.fromEntity(salvo);
    }
}