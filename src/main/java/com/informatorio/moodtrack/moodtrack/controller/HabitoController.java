package com.informatorio.moodtrack.moodtrack.controller;

import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoDto;
import com.informatorio.moodtrack.moodtrack.service.habito.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    @Autowired
    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    @GetMapping
    public List<HabitoDto> getHabitos() {
        return habitoService.getAllHabitos();
    }

    @PostMapping
    public ResponseEntity<HabitoDto> createHabito(@RequestBody HabitoCreateDto habitoCreateDto) {
        HabitoDto habitoCreado =  habitoService.createHabito(habitoCreateDto);
        return ResponseEntity.ok().body(habitoCreado);
    }

}
