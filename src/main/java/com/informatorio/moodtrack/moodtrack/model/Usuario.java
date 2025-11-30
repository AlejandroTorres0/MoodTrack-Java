package com.informatorio.moodtrack.moodtrack.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private PerfilUsuario perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private List<EntradaDiaria> entradasDiarias;

    public Usuario() {}

    public Usuario(UUID id, String nombre, String email, PerfilUsuario perfil, List<EntradaDiaria> entradasDiarias) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.perfil = perfil;
        this.entradasDiarias = entradasDiarias;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public List<EntradaDiaria> getEntradasDiarias() {
        return entradasDiarias;
    }

    public void setEntradasDiarias(List<EntradaDiaria> entradasDiarias) {
        this.entradasDiarias = entradasDiarias;
    }
}
