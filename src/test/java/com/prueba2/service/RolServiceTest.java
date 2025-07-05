package com.prueba2.service;

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
import com.prueba2.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {
    
    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void testGuardar(){
        //Rol
        Rol rol = new Rol(20L, "OWNER");
        when(rolRepository.save(rol)).thenReturn(rol);

        Rol resultadoRol = rolService.crear(rol);

        assertNotNull(resultadoRol);
        assertEquals("OWNER", resultadoRol.getNombre_rol());
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    void buscarRol(){
        Rol rol = new Rol(20L, "OWNER");

        when(rolRepository.findById(20L)).thenReturn(Optional.of(rol));

        Optional<Rol> resultadoRol = rolService.obtenerPorId(20L);

        assertNotNull(resultadoRol);
        assertEquals("OWNER", resultadoRol.get().getNombre_rol());
        verify(rolRepository, times(1)).findById(20L);
    }

    @Test
    void eliminarRol(){
        Long idRol = 20L;

        doNothing().when(rolRepository).deleteById(idRol);
        rolService.eliminar(idRol);

        verify(rolRepository, times(1)).deleteById(idRol);
    }
}
