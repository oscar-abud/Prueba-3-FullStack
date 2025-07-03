package com.prueba2.repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.prueba2.model.Curso;
import com.prueba2.model.Usuario;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursoRepositoryIntegrationTest {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    // usuario
    Usuario creador = usuarioRepository.findById("12345678-9")
    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
    @Test
    @Order(1)
    void crearCurso() {


        // curso
        Curso curso = new Curso(104L, "NodeJs", "JavaScript en el Backend", "EN PROCESO", creador);
        Curso guardado = cursoRepository.save(curso);

        assertNotNull(guardado.getId_curso());
        assertEquals("NodeJs", guardado.getNombre_curso());
    }

    @Test
    @Order(2)
    void actualizarCurso(){
        //Por si ecuentra el curso o no
        Optional<Curso> optionalCurso = cursoRepository.findById(104L);
        assertTrue(optionalCurso.isPresent(), "NodeJs");

        Curso curso = optionalCurso.get();
        curso.setNombre_curso("ExpressJs");

    }
}
