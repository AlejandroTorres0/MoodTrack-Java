package com.informatorio.moodtrack.moodtrack.dto.entradadiaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDiariaCreateDto {
    @NotNull(message = "El id de usuario es obligatorio")
    private UUID usuarioId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotBlank(message = "La reflexion no puede ser vacia")
    @Size(max = 500, message = "La reflexion no puede superar los 500 caracteres")
    private String reflexion;

    @NotBlank(message = "La emocion no puede ser vacia")
    @Size(max = 100, message = "La emocion no puede superar los 100 caracteres")
    private String emocion;

    private List<Long> habitosIds;
}