package com.jeictechnology.literAlura.repository;

import com.jeictechnology.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
}
