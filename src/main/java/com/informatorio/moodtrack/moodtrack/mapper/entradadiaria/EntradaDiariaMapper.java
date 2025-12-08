package com.informatorio.moodtrack.moodtrack.mapper.entradadiaria;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;
import com.informatorio.moodtrack.moodtrack.mapper.usuario.UsuarioMapper;
import com.informatorio.moodtrack.moodtrack.model.EntradaDiaria;

import java.util.List;

public final class EntradaDiariaMapper {
    public EntradaDiariaMapper() {}

    public static EntradaDiariaDto toDto(EntradaDiaria entradaDiaria) {
        if(entradaDiaria == null) return null;

        EntradaDiariaDto dto = new EntradaDiariaDto();
        dto.setId(entradaDiaria.getId());
        dto.setFecha(entradaDiaria.getFecha());
        dto.setEmocion(entradaDiaria.getEmocion());
        dto.setReflexion(entradaDiaria.getReflexion());
        dto.setUsuarioDto(UsuarioMapper.toDto(entradaDiaria.getUsuario()));

        if (entradaDiaria.getHabitos() != null && !entradaDiaria.getHabitos().isEmpty()){
            dto.setHabitosDescripciones(
                entradaDiaria.getHabitos().stream()
                        .map(habito -> habito.getDescripcion())
                        .toList()
            );
        }

        return dto;
    }

    public static List<EntradaDiariaDto> toDtoList(List<EntradaDiaria> entradas) {
        return entradas.stream()
                .map(entrada -> toDto(entrada))
                .toList();
    }
}
