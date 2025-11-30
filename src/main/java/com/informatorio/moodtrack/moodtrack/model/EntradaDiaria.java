package com.informatorio.moodtrack.moodtrack.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class EntradaDiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 500)
    private String reflexion;

    @Column(nullable = false, length = 100)
    private String emocion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany()
    @JoinTable(
            name = "entrada_habito",
            joinColumns = @JoinColumn(name = "entrada_id"),
            inverseJoinColumns = @JoinColumn(name = "habito_id")
    )
    private List<Habito> habitos;

    public EntradaDiaria() {}

    public EntradaDiaria(Long id, LocalDate fecha, String reflexion, String emocion, Usuario usuario, List<Habito> habitos) {
        this.id = id;
        this.fecha = fecha;
        this.reflexion = reflexion;
        this.emocion = emocion;
        this.usuario = usuario;
        this.habitos = habitos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getReflexion() {
        return reflexion;
    }

    public void setReflexion(String reflexion) {
        this.reflexion = reflexion;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Habito> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<Habito> habitos) {
        this.habitos = habitos;
    }
}
