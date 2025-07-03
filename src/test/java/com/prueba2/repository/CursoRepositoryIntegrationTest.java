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
    private Usuario getCreador(){

        return usuarioRepository.findById("12345678-9")
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @Test
    @Order(1)
    void crearCurso() {
        //Usuario
        Usuario creador = getCreador();
        // curso
        Curso curso = new Curso(104L, "NodeJs", "JavaScript en el Backend", "EN PROCESO", creador);
        Curso guardado = cursoRepository.save(curso);

        assertNotNull(guardado.getId_curso());
        assertEquals("NodeJs", guardado.getNombre_curso());
    }

    @Test
    @Order(2)
    void actualizarCurso(){
        //Usuario
        Usuario creador = getCreador();
        //Por si ecuentra el curso o no
        Optional<Curso> optionalCurso = cursoRepository.findById(104L);
        assertTrue(optionalCurso.isPresent(), "NodeJs");

        Curso curso = optionalCurso.get();
        curso.setNombre_curso("ExpressJs");
        curso.setDescripcion("FrameWork del JS para el Backend");
        curso.setEstado("EN PROCESO");
        curso.setCreador(creador);

        //Guardando curso
        cursoRepository.save(curso);

        Optional<Curso> cursoActualizado = cursoRepository.findById(104L);
        //Devuelve un true o false si el producto se actualizo o no
        assertTrue(cursoActualizado.isPresent());
        //Preguntamos si el nombre que le pasamos es igual al nombre del curso actualizado
        assertEquals("ExpressJs", cursoActualizado.get().getNombre_curso());
        assertEquals("FrameWork del JS para el Backend", cursoActualizado.get().getDescripcion());
        assertEquals("EN PROCESO", cursoActualizado.get().getEstado());
        assertEquals(creador.getRut(), cursoActualizado.get().getCreador().getRut());
    }

    @Test
    @Order(3)
    void buscarCurso(){
        Optional<Curso> cursoOptional = cursoRepository.findById(104L);
        assertTrue(cursoOptional.isPresent());
        assertEquals("ExpressJs", cursoOptional.get().getNombre_curso());
    }

    @Test
    @Order(4)
    void eliminarCurso(){
        Optional<Curso> cursoOptional = cursoRepository.findById(104L);
        assertTrue(cursoOptional.isPresent());
        assertEquals("ExpressJs", cursoOptional.get().getNombre_curso());

        //Si el producto existe lo eliminaremos
        cursoRepository.deleteById(104L);

        Optional<Curso> cursoEliminado = cursoRepository.findById(104L);
        assertFalse(cursoEliminado.isPresent(), "El curso no fue eliminado");

    }
}
