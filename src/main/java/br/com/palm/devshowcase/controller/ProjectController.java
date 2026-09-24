package br.com.palm.devshowcase.controller;

import java.net.URI;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.palm.devshowcase.dto.FeedbackRequestDTO;
import br.com.palm.devshowcase.dto.FeedbackResponseDTO;
import br.com.palm.devshowcase.dto.ProjectRequestDTO;
import br.com.palm.devshowcase.dto.ProjectResponseDTO;
import br.com.palm.devshowcase.dto.ProjectTechnologyRequestDTO;
import br.com.palm.devshowcase.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Operation(summary = "Lista projetos com filtro por tecnologia e paginação")
    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> listar(
            @RequestParam(required = false) String technology,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(service.listar(technology, pageable));
    }

    @Operation(summary = "Cadastra um novo projeto")
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> cadastrar(@Valid @RequestBody ProjectRequestDTO dto) {
        ProjectResponseDTO novoProjeto = service.cadastrar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoProjeto.id())
                .toUri();
        return ResponseEntity.created(location).body(novoProjeto);
    }

    @Operation(summary = "Incremente as curtidas de um projeto")
    @PutMapping("/{id}/upvote")
    public ResponseEntity<ProjectResponseDTO> upvote(@PathVariable Long id) {
        return ResponseEntity.ok(service.upvote(id));
    }

    @Operation(summary = "Cadastra um feedback e recalcula a nota média do projeto")
    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> cadastrarFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO dto) {
        FeedbackResponseDTO feedbackSalvo = service.adicionarFeedback(id, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{feedbackId}")
                .buildAndExpand(feedbackSalvo.id())
                .toUri();
        return ResponseEntity.created(location).body(feedbackSalvo);
    }

    @Operation(summary = "Vincula uma tecnologia a um projeto")
    @PostMapping("/{id}/technologies")
    public ResponseEntity<ProjectResponseDTO> vincularTecnologia(
            @PathVariable Long id,
            @Valid @RequestBody ProjectTechnologyRequestDTO dto) {
        return ResponseEntity.ok(service.vincularTecnologia(id, dto.technologyId()));
    }
}
