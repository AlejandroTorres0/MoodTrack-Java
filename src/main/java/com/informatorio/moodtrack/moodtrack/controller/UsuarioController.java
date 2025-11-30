package com.informatorio.moodtrack.moodtrack.controller;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.model.Usuario;
import com.informatorio.moodtrack.moodtrack.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable(name = "id") UUID id) {
        Optional<UsuarioDto> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioCreateDto usuarioCreateDto) {
        UsuarioDto usuarioCreado = usuarioService.createUsuario(usuarioCreateDto);
        return  ResponseEntity.ok().body(usuarioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario( @PathVariable(name = "id") UUID id, @Valid @RequestBody UsuarioCreateDto usuarioCreateDto){
        log.info("Solicitud para actualizar usuario con id {}", id);
        UsuarioDto usuarioDto = usuarioService.updateUsuario( id, usuarioCreateDto );
        if( usuarioDto == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( usuarioDto );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario( @PathVariable(name = "id") UUID id ){
        boolean wasDeleted = usuarioService.deleteUsuario( id );
        if( !wasDeleted ){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
