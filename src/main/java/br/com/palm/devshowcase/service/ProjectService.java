package br.com.palm.devshowcase.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.palm.devshowcase.dto.FeedbackRequestDTO;
import br.com.palm.devshowcase.dto.FeedbackResponseDTO;
import br.com.palm.devshowcase.dto.ProjectRequestDTO;
import br.com.palm.devshowcase.dto.ProjectResponseDTO;
import br.com.palm.devshowcase.exception.ResourceNotFoundException;
import br.com.palm.devshowcase.model.Feedback;
import br.com.palm.devshowcase.model.Profile;
import br.com.palm.devshowcase.model.Project;
import br.com.palm.devshowcase.model.Technology;
import br.com.palm.devshowcase.repository.FeedbackRepository;
import br.com.palm.devshowcase.repository.ProfileRepository;
import br.com.palm.devshowcase.repository.ProjectRepository;
import br.com.palm.devshowcase.repository.TechnologyRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> listar(String technology, Pageable pageable) {
        if (technology != null && !technology.isBlank()) {
            return projectRepository.findByTechnologies_NameIgnoreCase(technology.trim(), pageable)
                    .map(ProjectResponseDTO::fromEntity);
        }
        return projectRepository.findAll(pageable)
                .map(ProjectResponseDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO buscarPorId(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("projeto não encontrado com o id: " + id));
        return ProjectResponseDTO.fromEntity(project);
    }

    @Transactional
    public ProjectResponseDTO cadastrar(ProjectRequestDTO dto) {
        Profile profile = profileRepository.findById(dto.profileId())
                .orElseThrow(() -> new ResourceNotFoundException("perfil associado não encontrado com o id: " + dto.profileId()));

        Project project = new Project();
        project.setTitle(dto.title().trim());
        project.setDescription(dto.description().trim());
        project.setRepositoryUrl(dto.repositoryUrl().trim());
        project.setLiveDemoUrl(dto.liveDemoUrl());
        project.setProfile(profile);

        if (dto.technologyIds() != null && !dto.technologyIds().isEmpty()) {
            Set<Technology> techs = new HashSet<>(technologyRepository.findAllById(dto.technologyIds()));
            project.setTechnologies(techs);
        }

        Project salvo = projectRepository.save(project);
        return ProjectResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ProjectResponseDTO upvote(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("projeto não encontrado com o id: " + id));

        project.setLikes(project.getLikes() + 1);
        Project salvo = projectRepository.save(project);
        return ProjectResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public FeedbackResponseDTO adicionarFeedback(Long id, FeedbackRequestDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("projeto não encontrado com o id: " + id));

        Feedback feedback = new Feedback(
            dto.author().trim(),
            dto.comment().trim(),
            dto.rating(),
            project
        );

        Feedback feedbackSalvo = feedbackRepository.save(feedback);
        project.getFeedbacks().add(feedbackSalvo);
        project.recalculateAverageRating();
        projectRepository.save(project);

        return FeedbackResponseDTO.fromEntity(feedbackSalvo);
    }

    @Transactional
    public ProjectResponseDTO vincularTecnologia(Long projectId, Long technologyId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("projeto não encontrado com o id: " + projectId));

        Technology technology = technologyRepository.findById(technologyId)
                .orElseThrow(() -> new ResourceNotFoundException("tecnologia não encontrada com o id: " + technologyId));

        project.getTechnologies().add(technology);
        Project salvo = projectRepository.save(project);
        return ProjectResponseDTO.fromEntity(salvo);
    }
}
