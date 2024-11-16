package com.jeictechnology.literAlura.repository;

import com.jeictechnology.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTituloAndAutorName(String titulo, String autor);

    List<Libro> findByLenguaje(String lenguaje);

}
