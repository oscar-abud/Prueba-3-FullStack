package com.prueba2.repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.prueba2.model.Rol;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RolRepositoryIntegrationTest {
    @Autowired
    private RolRepository rolRepository;

    @Test
    @Order(1)
    void crearRol(){
        Rol rol = new Rol(10L, "VISITA");
        Rol rolGuardado = rolRepository.save(rol);

        assertNotNull(rolGuardado.getId_rol());
        assertEquals("VISITA", rolGuardado.getNombre_rol());
    }

    @Test
    @Order(2)
    void ActualizarRol(){

        Optional<Rol> optionalRol = rolRepository.findById(10L);
        assertTrue(optionalRol.isPresent(), "VISITA");

        Rol rol = optionalRol.get();
        rol.setNombre_rol("PROFESOR");

        rolRepository.save(rol);

        Optional<Rol> rolActualizado = rolRepository.findById(10L);
        assertTrue(rolActualizado.isPresent());
        assertEquals("PROFESOR", rolActualizado.get().getNombre_rol());
    }

    @Test
    @Order(3)
    void buscarRol(){
        Optional<Rol> rolOptional = rolRepository.findById(10L);
        assertTrue(rolOptional.isPresent());
        assertEquals("PROFESOR", rolOptional.get().getNombre_rol());
    }

    @Test
    @Order(4)
    void eliminarRol(){
        Optional<Rol> rol = rolRepository.findById(10L);
        assertTrue(rol.isPresent(), "El Rol no existe antes de poder eliminarlo");

        rolRepository.deleteById(10L);
        Optional<Rol> rolEliminado = rolRepository.findById(10L);
        assertFalse(rolEliminado.isPresent(), "El rol aun existe despues de ser eliminado");
    }
}
