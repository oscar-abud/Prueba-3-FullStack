package com.prueba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {}
