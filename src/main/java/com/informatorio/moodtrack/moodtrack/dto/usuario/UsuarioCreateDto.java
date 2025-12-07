package com.informatorio.moodtrack.moodtrack.dto.usuario;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.informatorio.moodtrack.moodtrack.dto.perfil.PerfilUsuarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDto {

    @NotBlank(message = "El nombre del usuario no puede ser vacio")
    private String nombre;

    @NotBlank(message = "El email del usuario no puede ser vacio")
    private String email;

    @Valid
    @NotNull
    @JsonProperty("perfil")
    private PerfilUsuarioDto perfilUsuarioDto;
}
