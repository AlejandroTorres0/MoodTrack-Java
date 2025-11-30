package com.informatorio.moodtrack.moodtrack.dto.perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDto {
    private Long id;
    private String bio;
    private String colorFavorito;
    private String fraseDelDia;
}
