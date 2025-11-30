package com.informatorio.moodtrack.moodtrack.dto.habito;

import com.informatorio.moodtrack.moodtrack.model.NivelDeImportanciaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HabitoCreateDto {
    @NotBlank(message = "La descripción no puede ser vacia")
    private String descripcion;

    @NotNull(message = "El nivel de importancia no puede ser nulo")
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;
}
