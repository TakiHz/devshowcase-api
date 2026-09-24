package br.com.palm.devshowcase.controller;

import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.palm.devshowcase.dto.ProfileDetailResponseDTO;
import br.com.palm.devshowcase.dto.ProfileRequestDTO;
import br.com.palm.devshowcase.dto.ProfileResponseDTO;
import br.com.palm.devshowcase.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @Operation(summary = "Lista todos os perfis")
    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Operation(summary = "Cadastra um novo perfil")
    @PostMapping
    public ResponseEntity<ProfileResponseDTO> cadastrar(@Valid @RequestBody ProfileRequestDTO dto) {
        ProfileResponseDTO novoPerfil = service.cadastrar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPerfil.id())
                .toUri();
        return ResponseEntity.created(location).body(novoPerfil);
    }

    @Operation(summary = "Busca um perfil pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDetailResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
