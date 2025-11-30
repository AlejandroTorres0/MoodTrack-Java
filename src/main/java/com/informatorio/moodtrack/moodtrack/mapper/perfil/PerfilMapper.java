package com.informatorio.moodtrack.moodtrack.mapper.perfil;

import com.informatorio.moodtrack.moodtrack.dto.perfil.PerfilUsuarioDto;
import com.informatorio.moodtrack.moodtrack.model.PerfilUsuario;

public final class PerfilMapper {

    public PerfilMapper() {}

    public static PerfilUsuarioDto toDto(PerfilUsuario perfilUsuario){
        if(perfilUsuario == null) return null;

        PerfilUsuarioDto perfilUsuarioDto = new PerfilUsuarioDto();
        perfilUsuarioDto.setId(perfilUsuario.getId());
        perfilUsuarioDto.setBio(perfilUsuario.getBio());
        perfilUsuarioDto.setColorFavorito(perfilUsuario.getColorFavorito());
        perfilUsuarioDto.setFraseDelDia(perfilUsuario.getFraseDelDia());
        return perfilUsuarioDto;
    }

    public static PerfilUsuario toEntity(PerfilUsuarioDto perfilUsuarioDto){
        if(perfilUsuarioDto == null) return null;
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setBio( perfilUsuarioDto.getBio() );
        perfilUsuario.setColorFavorito( perfilUsuarioDto.getColorFavorito() );
        perfilUsuario.setFraseDelDia( perfilUsuarioDto.getFraseDelDia() );
        return perfilUsuario;
    }
}
