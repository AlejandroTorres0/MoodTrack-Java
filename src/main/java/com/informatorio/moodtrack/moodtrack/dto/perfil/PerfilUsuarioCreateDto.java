package com.informatorio.moodtrack.moodtrack.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioCreateDto {
    @NotBlank(message = "La biografia no puede ser vacia")
    private String bio;

    @NotBlank(message = "El color favorito no puede ser vacio")
    private String colorFavorito;

    @NotBlank(message = "La Frase del dia no puede ser vacia")
    private String fraseDelDia;
}
