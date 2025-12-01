package com.informatorio.moodtrack.moodtrack.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;

    @ManyToMany(mappedBy = "habitos")
    private List<EntradaDiaria> entradas;

    public Habito () {}

    public Habito(Long id, String descripcion, NivelDeImportanciaEnum nivelDeImportanciaEnum, List<EntradaDiaria> entradas) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivelDeImportanciaEnum = nivelDeImportanciaEnum;
        this.entradas = entradas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public NivelDeImportanciaEnum getNivelDeImportanciaEnum() {
        return nivelDeImportanciaEnum;
    }

    public void setNivelDeImportanciaEnum(NivelDeImportanciaEnum nivelDeImportanciaEnum) {
        this.nivelDeImportanciaEnum = nivelDeImportanciaEnum;
    }

    public List<EntradaDiaria> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaDiaria> entradas) {
        this.entradas = entradas;
    }
}
