package com.prueba2.service;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.prueba2.model.Curso;
import com.prueba2.model.Rol;
import com.prueba2.model.Usuario;
import com.prueba2.repository.CursoRepository;
import com.prueba2.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    //simular el comportamiento de clases o interfaces (como repositorios) sin ejecutar su lógica real
    @Mock
    private CursoRepository cursoRepository;


    @InjectMocks
    private CursoService cursoService;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Test
    void testGuardarCurso(){

        //Crear Rol (si tu modelo tiene nombre, id, etc.)
        Rol rol = new Rol();
        rol.setId_rol(1L);
        rol.setNombre_rol("ADMIN");

        //Usuario
        Usuario usuario = new Usuario();
        usuario.setRut("12345678-9");
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setMail("juan.perez@example.com");
        usuario.setContrasena("1234segura");
        usuario.setfechaRegistro(new Date());
        usuario.setRol(rol);

        Curso curso = new Curso(106L, "Python", "Aprendiendo Python", "TERMINADO", usuario);
        when(cursoRepository.save(curso)).thenReturn(curso);

        Curso resultadoCurso = cursoService.crear(curso);

        assertNotNull(resultadoCurso);
        assertEquals("Python", resultadoCurso.getNombre_curso());
        verify(cursoRepository, times(1)).save(curso);
    }

}
