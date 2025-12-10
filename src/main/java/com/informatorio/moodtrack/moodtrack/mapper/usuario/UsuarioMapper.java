package com.informatorio.moodtrack.moodtrack.mapper.usuario;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenEmocionesDto;
import com.informatorio.moodtrack.moodtrack.mapper.perfil.PerfilMapper;
import com.informatorio.moodtrack.moodtrack.model.EntradaDiaria;
import com.informatorio.moodtrack.moodtrack.model.Usuario;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class UsuarioMapper {
    private UsuarioMapper() {}

    public static UsuarioDto toDto(Usuario usuario) {
        if(usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPerfilUsuarioDto(PerfilMapper.toDto(usuario.getPerfil()));
        return usuarioDto;
    };

    public static UsuarioResumenDto toResumenDto(Usuario usuario) {
        if(usuario == null) return null;
        UsuarioResumenDto usuarioResumenDto = new UsuarioResumenDto();

        usuarioResumenDto.setNombre(usuario.getNombre());
        usuarioResumenDto.setEmail(usuario.getEmail());
        usuarioResumenDto.setColorFavorito(usuario.getPerfil().getColorFavorito());
        usuarioResumenDto.setCantidadEntradas(usuario.getEntradasDiarias().size());
        usuario.getFechaUltimaEntrada().ifPresent(fecha -> usuarioResumenDto.setFechaUltimaEntrada(fecha));

        return usuarioResumenDto;
    };

    public static UsuarioResumenEmocionesDto toResumenEmocionesDto(Usuario usuario) {
        if(usuario == null) return null;
        UsuarioResumenEmocionesDto usuarioResumenEmocionesDto = new UsuarioResumenEmocionesDto();

        if (usuario.getEntradasDiarias().isEmpty()) {
            usuarioResumenEmocionesDto.setTotalEntradas(0);
            usuarioResumenEmocionesDto.setPorcentajesPorEmocion(Collections.emptyMap());
            return usuarioResumenEmocionesDto;
        }

        int totalEntradas =  usuario.getEntradasDiarias().size();

        Map<String, Long> conteoPorEmocion = usuario.getEntradasDiarias().stream()
                .collect(Collectors.groupingBy(
                        EntradaDiaria::getEmocion,
                        Collectors.counting()
                ));

        Map<String, Double> porcentajesPorEmocion = conteoPorEmocion.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (double) Math.round(entry.getValue() * 10000.0 / totalEntradas) / 100.0
                ));

        usuarioResumenEmocionesDto.setTotalEntradas(totalEntradas);
        usuarioResumenEmocionesDto.setPorcentajesPorEmocion(porcentajesPorEmocion);
        return usuarioResumenEmocionesDto;
    }

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
