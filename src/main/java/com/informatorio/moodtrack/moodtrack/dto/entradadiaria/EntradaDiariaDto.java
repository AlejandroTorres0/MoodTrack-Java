package com.informatorio.moodtrack.moodtrack.dto.entradadiaria;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDiariaDto {

    private Long id;
    private LocalDate fecha;
    private String reflexion;
    private UsuarioDto usuarioDto;
    private String emocion;
    private List<String> habitosDescripciones;

}
