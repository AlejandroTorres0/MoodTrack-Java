package com.informatorio.moodtrack.moodtrack.repository.entradadiaria;

import com.informatorio.moodtrack.moodtrack.model.EntradaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EntradaDiariaRepository extends JpaRepository<EntradaDiaria, Long> {
    List<EntradaDiaria> getEntradasByUsuarioId(UUID id);
}
