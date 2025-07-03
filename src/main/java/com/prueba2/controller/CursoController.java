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

import com.prueba2.model.Curso;
import com.prueba2.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    public CursoController(){

    }

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listar(){
        return cursoService.listar();
    }

    @GetMapping("/{id_curso}")
    public Optional<Curso> obtenerPorId(@PathVariable Long id_curso){
        return cursoService.obtenerPorId(id_curso);
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso){
        return cursoService.crear(curso);
    }

    @PutMapping("/{id_curso}")
    public Curso actualizar(@PathVariable Long id_curso, @RequestBody Curso curso){
        return cursoService.actualizar(id_curso, curso);
    }

    @DeleteMapping("/{id_curso}")
    public void eliminar(@PathVariable Long id_curso){
        cursoService.eliminar(id_curso);
    }

}
