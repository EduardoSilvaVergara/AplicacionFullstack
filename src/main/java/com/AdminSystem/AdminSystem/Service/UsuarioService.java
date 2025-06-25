package com.AdminSystem.AdminSystem.Service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;

    public UsuarioService(UsuarioRepository usuarioRepo, RolRepository rolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
    }


    // Crear usuario
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepo.existsByNombreUsuario(usuario.getNombreUsuario())) {
        throw new RuntimeException("El usuario ya existe");
        }
    
        Rol rolCompleto = rolRepo.findById(usuario.getRol().getId())
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rolCompleto);
        return usuarioRepo.save(usuario);
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario datosUsuario) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombreUsuario(datosUsuario.getNombreUsuario());
        usuario.setCorreo(datosUsuario.getCorreo());
        usuario.setContrasena(datosUsuario.getContrasena());
        usuario.setRol(datosUsuario.getRol());
        return usuarioRepo.save(usuario);
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

    // Listar usuarios
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepo.findAll();
    }

    
    // Configurar permisos (actualizar rol/permisos)
    public Rol actualizarPermisosRol(Long idRol, String permisos) {
        Rol rol = rolRepo.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        rol.setPermisos(permisos);
        return rolRepo.save(rol);
    }

    public List<Rol> obtenerRoles() {
        return rolRepo.findAll();
    }
}