package com.informatorio.moodtrack.moodtrack.controller;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;
import com.informatorio.moodtrack.moodtrack.service.entradadiaria.EntradaDiariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/entrada-diaria")
@RequiredArgsConstructor
@Validated
@Slf4j
public class EntradaController {
    private final EntradaDiariaService entradaDiariaService;

    @PostMapping
    public ResponseEntity<EntradaDiariaDto> createEntradaDiaria(@Valid @RequestBody EntradaDiariaCreateDto createDto){
        try {
            EntradaDiariaDto entradaCreada = entradaDiariaService.createEntradaDiaria( createDto );
            return ResponseEntity
                    .created(URI.create("/api/v1/entrada-diaria" + entradaCreada.getId()))
                    .body(entradaCreada);
        }catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error("Error desconocido", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
