package com.prueba2.repository;

import java.util.Date;
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

import com.prueba2.model.Rol;
import com.prueba2.model.Usuario;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioRepositoryIntegrationTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    //Rol
    private Rol getRol(){
        return rolRepository.findById(3L)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Test
    @Order(1)
    void crearUsuario(){
        Rol rol = getRol();

        //Creando el usuario
        Usuario usuario = new Usuario("98765432-1", "Naruto", "Uzumaki", "nuzumaki@hotmail.com", "hokage123", new Date(), rol);
        Usuario guardado = usuarioRepository.save(usuario);

        assertNotNull(guardado.getRut());
        assertEquals("Naruto", guardado.getNombre());
    }

    @Test()
    @Order(2)
    void actualizarUsuario(){
        //Rol
        Rol rol = getRol();
        Optional<Usuario> usuarioOptional = usuarioRepository.findById("98765432-1");
        assertTrue(usuarioOptional.isPresent(), "Naruto");

        Usuario usuario = usuarioOptional.get();
        usuario.setNombre("Erwin");
        usuario.setApellido("Smith");
        usuario.setMail("ermith@gmail.com");
        usuario.setContrasena("shinzusasageyo");
        usuario.setfechaRegistro(new Date());
        usuario.setRol(rol);

        usuarioRepository.save(usuario);

        Optional<Usuario> usuarioActualizado = usuarioRepository.findById("98765432-1");

        assertEquals("Erwin", usuarioActualizado.get().getNombre());
        assertEquals("Smith", usuarioActualizado.get().getApellido());
        assertEquals("ermith@gmail.com", usuarioActualizado.get().getMail());
        assertEquals("shinzusasageyo", usuarioActualizado.get().getContrasena());
        assertEquals(rol.getId_rol(), usuarioActualizado.get().getRol().getId_rol());
    }

    @Test
    @Order(3)
    void buscarUsuario(){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById("98765432-1");
        assertTrue(usuarioOptional.isPresent());
        assertEquals("Erwin", usuarioOptional.get().getNombre());
    }
}
