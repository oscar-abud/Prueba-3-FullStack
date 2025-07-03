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

import com.prueba2.model.Rol;
import com.prueba2.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<Rol> listar(){
        return rolService.listar();
    }

    @GetMapping("/{id_rol}")
    public Optional<Rol> obtenerPorId(@PathVariable Long id_rol){
        return rolService.obtenerPorId(id_rol);
    }

    @PostMapping
    public Rol crear(@RequestBody Rol rol){
        return rolService.crear(rol);
    }

    @PutMapping("/{id_rol}")
    public Rol actualizar(@PathVariable Long id_rol, @RequestBody Rol rol){
        return rolService.actualizar(id_rol, rol);
    }

    @DeleteMapping("/{id_rol}")
    public void eliminar(@PathVariable Long id_rol){
        rolService.eliminar(id_rol);
    }

}
