package com.informatorio.moodtrack.moodtrack.dto.entradadiaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDiariaUpdateDto {

    @NotBlank(message = "La reflexion no puede ser vacia")
    @Size(max = 500, message = "La reflexion no puede superar los 500 caracteres")
    private String reflexion;

    @NotBlank(message = "La emocion no puede ser vacia")
    @Size(max = 100, message = "La emocion no puede superar los 100 caracteres")
    private String emocion;

    private List<Long> habitosIds;
}
