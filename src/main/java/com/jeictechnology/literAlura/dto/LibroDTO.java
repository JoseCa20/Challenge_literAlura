package com.jeictechnology.literAlura.dto;

public record LibroDTO(
        Long id,
        String titulo,
        String autores,
        String lenguaje,
        Integer descargas
) {
}
