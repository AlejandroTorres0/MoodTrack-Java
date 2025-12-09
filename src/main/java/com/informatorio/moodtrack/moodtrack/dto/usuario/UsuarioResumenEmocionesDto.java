package com.informatorio.moodtrack.moodtrack.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResumenEmocionesDto {
    private int totalEntradas;
    private Map<String, Double> porcentajesPorEmocion;
}
