package com.informatorio.moodtrack.moodtrack.service.usuario;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenEmocionesDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    List<UsuarioDto> getAllUsuarios(String nombre, String email, String colorFavorito);
    Optional<UsuarioDto> getUsuarioById(UUID id);

    UsuarioDto createUsuario(UsuarioCreateDto usuarioCreateDto);

    UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto);

    boolean deleteUsuario(UUID id);

    Optional<UsuarioResumenDto> getResumenUsuario(UUID id);
    Optional<UsuarioResumenEmocionesDto> getResumenEmocionesUsuario(UUID id);
}
