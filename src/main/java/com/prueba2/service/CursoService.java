package com.prueba2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba2.model.Curso;
import com.prueba2.repository.CursoRepository;

@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepo;

    public List<Curso> listar(){
        return cursoRepo.findAll();
    }

    public Optional<Curso> obtenerPorId(Long id){
        return cursoRepo.findById(id);
    }

    public Curso crear(Curso curso){
        return cursoRepo.save(curso);
    }

    public Curso actualizar(Long id, Curso curso){
        curso.setId_curso(id);
        return cursoRepo.save(curso);
    }

    public void eliminar(Long id){
        cursoRepo.deleteById(id);
    }
    
}
