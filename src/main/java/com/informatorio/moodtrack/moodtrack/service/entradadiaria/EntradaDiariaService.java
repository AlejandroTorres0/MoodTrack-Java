package com.informatorio.moodtrack.moodtrack.service.entradadiaria;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;

import java.util.List;
import java.util.UUID;

public interface EntradaDiariaService {
    EntradaDiariaDto createEntradaDiaria(EntradaDiariaCreateDto createDto);

    List<EntradaDiariaDto> getEntradasByUsuarioId(UUID id);
}
