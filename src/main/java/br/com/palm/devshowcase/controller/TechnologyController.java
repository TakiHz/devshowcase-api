package br.com.palm.devshowcase.controller;

import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.palm.devshowcase.dto.TechnologyRequestDTO;
import br.com.palm.devshowcase.dto.TechnologyResponseDTO;
import br.com.palm.devshowcase.service.TechnologyService;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService service;

    @Operation(summary = "Lista todas as tecnologias")
    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Operation(summary = "Cadastra uma nova tecnologia")
    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> cadastrar(@Valid @RequestBody TechnologyRequestDTO dto) {
        TechnologyResponseDTO novaTech = service.cadastrar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaTech.id())
                .toUri();
        return ResponseEntity.created(location).body(novaTech);
    }
}
