package com.jeictechnology.literAlura.dto;

public record LibroDTO(
        Long id,
        String titulo,
        String autor,
        String lenguaje,
        Integer descargas
) {
}
