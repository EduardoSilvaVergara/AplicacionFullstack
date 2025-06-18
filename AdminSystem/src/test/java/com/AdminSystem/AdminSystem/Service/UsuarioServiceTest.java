package com.AdminSystem.AdminSystem.Service;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepo;
    @Mock
    private RolRepository rolRepo;

    

    @Test
    void crearUsuario_ok() {
        Rol rol = new Rol(1L, "ADMIN", "ALL");
        Usuario usuario = new Usuario(null, "test", "mail@mail.com", "123", rol);

        when(usuarioRepo.existsByNombreUsuario("test")).thenReturn(false);
        when(rolRepo.findById(1L)).thenReturn(Optional.of(rol));
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.crearUsuario(usuario);

        assertEquals("test", result.getNombreUsuario());
        verify(usuarioRepo).save(any(Usuario.class));
    }

    @Test
    void crearUsuario_usuarioYaExiste() {
        Usuario usuario = new Usuario(null, "test", "mail@mail.com", "123", new Rol(1L, "ADMIN", "ALL"));
        when(usuarioRepo.existsByNombreUsuario("test")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> usuarioService.crearUsuario(usuario));
    }

    @Test
    void actualizarUsuario_ok() {
        Rol rol = new Rol(1L, "ADMIN", "ALL");
        Usuario usuario = new Usuario(1L, "test", "mail@mail.com", "123", rol);
        Usuario datos = new Usuario(null, "nuevo", "nuevo@mail.com", "456", rol);

        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.actualizarUsuario(1L, datos);

        assertEquals("nuevo", result.getNombreUsuario());
        verify(usuarioRepo).save(any(Usuario.class));
    }

    @Test
    void eliminarUsuario_ok() {
        usuarioService.eliminarUsuario(1L);
        verify(usuarioRepo).deleteById(1L);
    }

    @Test
    void obtenerUsuarios_ok() {
        when(usuarioRepo.findAll()).thenReturn(List.of());
        assertNotNull(usuarioService.obtenerUsuarios());
    }

    @Test
    void actualizarPermisosRol_ok() {
        Rol rol = new Rol(1L, "ADMIN", "ALL");
        when(rolRepo.findById(1L)).thenReturn(Optional.of(rol));
        when(rolRepo.save(any(Rol.class))).thenReturn(rol);

        Rol result = usuarioService.actualizarPermisosRol(1L, "NEW");
        assertEquals("NEW", result.getPermisos());
    }

    @Test
    void obtenerRoles_ok() {
        when(rolRepo.findAll()).thenReturn(List.of());
        assertNotNull(usuarioService.obtenerRoles());
    }
}