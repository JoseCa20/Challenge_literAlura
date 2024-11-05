package com.jeictechnology.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("languages")List<String> lenguajes,
        @JsonAlias("download_count")Integer descargas
) {
    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", autor=" + autores +
                ", lenguaje=" + lenguajes +
                ", descargas=" + descargas +
                '}';
    }
}
