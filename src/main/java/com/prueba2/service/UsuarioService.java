package com.prueba2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba2.model.Usuario;
import com.prueba2.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepo;

    public List<Usuario> listar(){
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> obtenerPorId(String rut){
        return usuarioRepo.findById(rut);
    }

    public Usuario crear(Usuario usu){
        return usuarioRepo.save(usu);
    }

    public Usuario actualizar(String rut, Usuario usu){
        usu.setRut(rut);
        return usuarioRepo.save(usu);
    }

    public void eliminar(String rut){
        usuarioRepo.deleteById(rut);
    }

}
