package com.informatorio.moodtrack.moodtrack.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.informatorio.moodtrack.moodtrack.dto.perfil.PerfilUsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private UUID id;
    private String nombre;
    private String email;

    @JsonProperty("perfil")
    private PerfilUsuarioDto perfilUsuarioDto;
}
