package com.informatorio.moodtrack.moodtrack.service.habito;

import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoDto;

import java.util.List;

public interface HabitoService {
    List<HabitoDto> getAllHabitos();

    HabitoDto createHabito(HabitoCreateDto habitoCreateDto);
}
