package com.jeictechnology.literAlura.repository;

import com.jeictechnology.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ILibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTituloAndAutorName(String titulo, String autor);

}
