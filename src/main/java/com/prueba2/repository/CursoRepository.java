package com.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba2.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
