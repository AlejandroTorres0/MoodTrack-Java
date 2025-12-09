package com.informatorio.moodtrack.moodtrack.service.entradadiaria.impl;

import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.entradadiaria.EntradaDiariaDto;
import com.informatorio.moodtrack.moodtrack.mapper.entradadiaria.EntradaDiariaMapper;
import com.informatorio.moodtrack.moodtrack.model.EntradaDiaria;
import com.informatorio.moodtrack.moodtrack.model.Habito;
import com.informatorio.moodtrack.moodtrack.model.Usuario;
import com.informatorio.moodtrack.moodtrack.repository.habito.HabitoRepository;
import com.informatorio.moodtrack.moodtrack.repository.usuario.UsuarioRepository;
import com.informatorio.moodtrack.moodtrack.repository.entradadiaria.EntradaDiariaRepository;
import com.informatorio.moodtrack.moodtrack.service.entradadiaria.EntradaDiariaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntradaDiariaServiceImpl implements EntradaDiariaService {

    private final EntradaDiariaRepository entradaDiariaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabitoRepository habitoRepository;


    @Override
    public EntradaDiariaDto createEntradaDiaria(EntradaDiariaCreateDto createDto) {
        log.info("Creando entrada Diaria");
        UUID uuidUsuario = createDto.getUsuarioId();

        Optional<Usuario> usuario = usuarioRepository.findById(uuidUsuario);

        if(usuario.isEmpty()){
            log.warn("Usuario no encontrado");
            throw new IllegalArgumentException("Usuario no encontrado id : " + uuidUsuario);
        }

        List<Habito> habitos = List.of();
        if(createDto.getHabitosIds() != null && !createDto.getHabitosIds().isEmpty()){
            habitos = habitoRepository.findAllById( createDto.getHabitosIds() );
            if(habitos.size() != createDto.getHabitosIds().size()){
                log.warn("Alguno de los habitos no se ha encontrado");
            }
        }

        EntradaDiaria entradaDiaria = new EntradaDiaria();
        entradaDiaria.setUsuario(usuario.get());
        entradaDiaria.setHabitos(habitos);
        entradaDiaria.setFecha(createDto.getFecha());
        entradaDiaria.setReflexion(createDto.getReflexion());
        entradaDiaria.setEmocion(createDto.getEmocion());

        EntradaDiaria saved = entradaDiariaRepository.save(entradaDiaria);

        log.info("Entrada guardado exitosamente");
        return EntradaDiariaMapper.toDto( saved );
    }

    @Override
    public List<EntradaDiariaDto> getEntradasByUsuarioId(UUID id) {
        List<EntradaDiaria> entradas = entradaDiariaRepository.findEntradasByUsuarioId(id);
        return EntradaDiariaMapper.toDtoList(entradas);

    }
}
