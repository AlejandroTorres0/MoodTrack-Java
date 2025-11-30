package com.informatorio.moodtrack.moodtrack.mapper.habito;

import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoDto;
import com.informatorio.moodtrack.moodtrack.model.Habito;

import java.util.List;

public final class HabitoMapper {
    private HabitoMapper() {}

    public static HabitoDto toDto(Habito habito) {
        if(habito == null) return null;

        HabitoDto habitoDto = new HabitoDto();
        habitoDto.setId(habito.getId());
        habitoDto.setDescripcion(habito.getDescripcion());
        return habitoDto;
    };

    public static List<HabitoDto> toDtoList(List<Habito> habitos) {
        return habitos.stream()
                .map(habito -> toDto(habito))
                .toList();
    }

    public static Habito  toEntity(HabitoCreateDto habitoCreateDto) {
        if(habitoCreateDto == null) return null;

        Habito habito = new Habito();
        habito.setDescripcion(habitoCreateDto.getDescripcion());
        habito.setNivelDeImportanciaEnum(habitoCreateDto.getNivelDeImportanciaEnum());
        return habito;
    }


}
