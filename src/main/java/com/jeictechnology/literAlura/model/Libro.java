package com.jeictechnology.literAlura.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autores;

    private String lenguajes;

    private Integer descargas;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores().stream()
                .findFirst()
                .map(Autor::nombre)
                .orElse("Desconocido");
        this.lenguajes = datosLibro.lenguajes().stream()
                .findFirst()
                .orElse("Desconocido");
        this.descargas = datosLibro.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "titulo = " + titulo + '\n' +
                "autores = " + autores + '\n' +
                "lenguaje = " + lenguajes + '\n' +
                "número total de descargas = " + descargas + '\n';
    }
}
