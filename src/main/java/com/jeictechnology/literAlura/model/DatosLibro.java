package com.jeictechnology.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages")List<String> lenguaje,
        @JsonAlias("download_count")Integer descargas
) {
    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", lenguaje=" + lenguaje +
                ", descargas=" + descargas +
                '}';
    }
}
