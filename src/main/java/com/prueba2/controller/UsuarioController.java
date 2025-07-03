package com.prueba2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba2.model.Usuario;
import com.prueba2.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/{rut}")
    public Optional<Usuario> obtenerPorId(@PathVariable String rut){
        return usuarioService.obtenerPorId(rut);
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){
        return usuarioService.crear(usuario);
    }
    
    @PutMapping("/{rut}")
    public Usuario actualizar(@PathVariable String rut, @RequestBody Usuario usuario){
        return usuarioService.actualizar(rut, usuario);
    }

    @DeleteMapping("/{rut}")
    public void eliminar(@PathVariable String rut){
        usuarioService.eliminar(rut);
    }

}
