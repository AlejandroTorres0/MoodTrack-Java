package com.informatorio.moodtrack.moodtrack.service.entradadiaria;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;

public interface EntradaDiariaService {
    EntradaDiariaDto create(EntradaDiariaCreateDto createDto);

}
