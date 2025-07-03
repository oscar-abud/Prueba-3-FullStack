package com.prueba2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba2.model.Rol;
import com.prueba2.repository.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listar(){
        return rolRepository.findAll();
    }

    public Optional<Rol> obtenerPorId(Long id){
        return rolRepository.findById(id);
    }

    public Rol crear(Rol rol){
        return rolRepository.save(rol);
    }

    public Rol actualizar(Long id, Rol rol){
        rol.setId_rol(id);
        return rolRepository.save(rol);
    }

    public void eliminar(Long id){
        rolRepository.deleteById(id);
    }

}
