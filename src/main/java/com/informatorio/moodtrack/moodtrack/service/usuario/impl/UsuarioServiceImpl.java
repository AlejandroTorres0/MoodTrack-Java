package com.informatorio.moodtrack.moodtrack.service.usuario.impl;

import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenDto;
import com.informatorio.moodtrack.moodtrack.dto.usuario.UsuarioResumenEmocionesDto;
import com.informatorio.moodtrack.moodtrack.mapper.perfil.PerfilMapper;
import com.informatorio.moodtrack.moodtrack.mapper.usuario.UsuarioMapper;
import com.informatorio.moodtrack.moodtrack.model.PerfilUsuario;
import com.informatorio.moodtrack.moodtrack.model.Usuario;
import com.informatorio.moodtrack.moodtrack.repository.usuario.UsuarioRepository;
import com.informatorio.moodtrack.moodtrack.repository.usuario.specification.UsuarioSpecifications;
import com.informatorio.moodtrack.moodtrack.service.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> getAllUsuarios(String nombre, String email, String colorFavorito) {
        Specification<Usuario> spec = Specification.unrestricted();

        if(nombre != null && !nombre.isBlank()){
            spec = spec.and(UsuarioSpecifications.nombre( nombre ));
        }
        if(email != null && !email.isBlank()){
            spec = spec.and(UsuarioSpecifications.email( email ));
        }
        if(colorFavorito != null && !colorFavorito.isBlank()){
            spec = spec.and(UsuarioSpecifications.colorFavorito( colorFavorito ));
        }

        List<Usuario> usuarioList = usuarioRepository.findAll(spec);

        return UsuarioMapper.toDtoList(usuarioList);
    }

    @Override
    public Optional<UsuarioDto> getUsuarioById(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioEntity = usuario.get();
            return Optional.of(UsuarioMapper.toDto(usuarioEntity));
        }
        return Optional.empty();
    }

    @Override
    public UsuarioDto createUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioCreateDto);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto) {
        //1. Buscar el usuario por id
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Optional<Usuario> usuarioExist = usuarioRepository.findByEmail( usuarioCreateDto.getEmail() );

            if (usuarioExist.isPresent() && !usuarioExist.get().getId().equals( id )) {
                throw new IllegalArgumentException("Mail no disponible");
            }

            //2. Setear campo a campo para actualizarlo
            Usuario usuarioEntity = usuario.get();
            usuarioEntity.setNombre( usuarioCreateDto.getNombre() );
            usuarioEntity.setEmail( usuarioCreateDto.getEmail() );

            PerfilUsuario perfilUsuario = usuarioEntity.getPerfil();
            //3. Chequear si no tiene perfil crearlo, sino actualizar sus campos
            if(perfilUsuario == null) {
                perfilUsuario = PerfilMapper.toEntity( usuarioCreateDto.getPerfilUsuarioDto() );
                usuarioEntity.setPerfil( perfilUsuario );
            }else{
                perfilUsuario.setBio( usuarioCreateDto.getPerfilUsuarioDto().getBio() );
                perfilUsuario.setColorFavorito( usuarioCreateDto.getPerfilUsuarioDto().getColorFavorito() );
                perfilUsuario.setFraseDelDia( usuarioCreateDto.getPerfilUsuarioDto().getFraseDelDia() );
            }

            //4. Guardarlo (actualizarlo)
            Usuario usuarioActualizado = usuarioRepository.save( usuarioEntity );
            log.info("Usuario actualizado con id {}", usuarioActualizado.getId());

            //5 devolver el UsuarioDTO.
            return UsuarioMapper.toDto( usuarioActualizado );

        }
        return null;
    }

    @Override
    public boolean deleteUsuario(UUID id) {

        if ( usuarioRepository.existsById(id) ){
            usuarioRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<UsuarioResumenDto> getResumenUsuario(UUID id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toResumenDto);
    }

    @Override
    public Optional<UsuarioResumenEmocionesDto> getResumenEmocionesUsuario(UUID id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toResumenEmocionesDto);

    }
}
