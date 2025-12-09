package com.informatorio.moodtrack.moodtrack.controller;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenEmocionesDto;
import com.informatorio.moodtrack.moodtrack.service.entradadiaria.EntradaDiariaService;
import com.informatorio.moodtrack.moodtrack.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EntradaDiariaService entradaDiariaService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, EntradaDiariaService entradaDiariaService) {
        this.usuarioService = usuarioService;
        this.entradaDiariaService = entradaDiariaService;
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

    @GetMapping("/{id}/resumen")
    public ResponseEntity<UsuarioResumenDto> getResumenUsuario(@PathVariable(name = "id") UUID id) {
        log.info("Construyendo resumen para el usuario con id {}", id);
        Optional<UsuarioResumenDto> usuarioResumen = usuarioService.getResumenUsuario(id);

        if (usuarioResumen.isPresent()) {
            return ResponseEntity.ok(usuarioResumen.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/resumen/emociones")
    public ResponseEntity<UsuarioResumenEmocionesDto> getResumenEmocionesUsuario(@PathVariable(name = "id") UUID id) {
        log.info("Construyendo resumen emocional para el usuario con id {}", id);
        Optional<UsuarioResumenEmocionesDto> usuarioResumenEmociones = usuarioService.getResumenEmocionesUsuario(id);

        if (usuarioResumenEmociones.isPresent()) {
            return ResponseEntity.ok(usuarioResumenEmociones.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/entradas-diarias")
    public ResponseEntity<List<EntradaDiariaDto>> getEntradasUsuario(@PathVariable(name = "id") UUID id) {
        Optional<UsuarioDto> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            List<EntradaDiariaDto> entradas = entradaDiariaService.getEntradasByUsuarioId(id);
            return ResponseEntity.ok(entradas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
        UsuarioDto usuarioCreado = usuarioService.createUsuario(usuarioCreateDto);
        return ResponseEntity
                .created(URI.create("/api/v1/usuarios" + usuarioCreado.getId()))
                .body(usuarioCreado);
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
