package com.AdminSystem.AdminSystem.Controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public UsuarioController(UsuarioService usuarioService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
    }

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/listar")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    

    @PostMapping("/crearRol")
    public Rol crearRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    @PutMapping("/permisos/{idRol}")
    public Rol actualizarPermisosRol(@PathVariable Long idRol, @RequestBody String permisos) {
        return usuarioService.actualizarPermisosRol(idRol, permisos);
    }

    @GetMapping("/roles")
    public List<Rol> obtenerRoles() {
        return usuarioService.obtenerRoles();
    }
}