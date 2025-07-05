package com.prueba2.service;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba2.model.Rol;
import com.prueba2.model.Usuario;
import com.prueba2.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    //Creando Rol
    private Rol getRol(){
        Rol rol = new Rol(2L, "USER");
        return rol;
    }

    @Test
    void testGuardarUsuario(){
        Usuario usuario = new Usuario("20962427-3", "Oscar", "Palma", "opalma@gmail.com", "COSA123", new Date(), getRol());

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultadoUsuario = usuarioService.crear(usuario);

        assertNotNull(resultadoUsuario);
        assertEquals("20962427-3", resultadoUsuario.getRut());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void buscarUsuario(){
        Usuario usuario = new Usuario("20962427-3", "Oscar", "Palma", "opalma@gmail.com", "COSA123", new Date(), getRol());

        when(usuarioRepository.findById("20962427-3")).thenReturn(Optional.of(usuario));

        Optional <Usuario> resultadoUsuario = usuarioService.obtenerPorId("20962427-3");

        assertNotNull(resultadoUsuario);
        assertEquals("20962427-3", resultadoUsuario.get().getRut());
        verify(usuarioRepository, times(1)).findById("20962427-3");
    }

    @Test
    void eliminarUsuario(){
        String idUsuario = "20962427-3";

        doNothing().when(usuarioRepository).deleteById(idUsuario);

        usuarioService.eliminar(idUsuario);

        verify(usuarioRepository, times(1)).deleteById("20962427-3");
    }
}
