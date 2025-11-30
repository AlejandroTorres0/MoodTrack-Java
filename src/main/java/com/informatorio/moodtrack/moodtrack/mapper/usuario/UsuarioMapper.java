package com.informatorio.moodtrack.moodtrack.mapper.usuario;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.mapper.perfil.PerfilMapper;
import com.informatorio.moodtrack.moodtrack.model.Usuario;

import java.util.List;

public final class UsuarioMapper {
    private UsuarioMapper() {}

    public static UsuarioDto toDto(Usuario usuario) {
        if(usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEmail(usuario.getEmail());
        return usuarioDto;
    };

    public static List<UsuarioDto> toDtoList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toDto(usuario))
                .toList();
    }

    public static Usuario toEntity(UsuarioCreateDto usuarioCreateDto) {
        if(usuarioCreateDto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioCreateDto.getEmail());
        usuario.setNombre(usuarioCreateDto.getNombre());
        usuario.setPerfil(PerfilMapper.toEntity(usuarioCreateDto.getPerfilUsuarioDto()));
        usuario.setEntradasDiarias(List.of());
        return usuario;
    }
}
