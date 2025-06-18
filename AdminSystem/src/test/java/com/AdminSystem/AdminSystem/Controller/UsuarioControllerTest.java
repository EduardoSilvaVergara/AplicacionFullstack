package com.AdminSystem.AdminSystem.Controller;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private RolRepository rolRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void crearUsuario_ok() throws Exception {
        Rol rol = new Rol(1L, "ADMIN", "ALL");
        Usuario usuario = new Usuario(null, "test", "mail@mail.com", "123", rol);

        when(usuarioService.crearUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreUsuario").value("test"));
    }

    @Test
    void actualizarUsuario_ok() throws Exception {
        Rol rol = new Rol(1L, "ADMIN", "ALL");
        Usuario usuario = new Usuario(1L, "test", "mail@mail.com", "123", rol);

        when(usuarioService.actualizarUsuario(Mockito.eq(1L), any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void eliminarUsuario_ok() throws Exception {
        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerUsuarios_ok() throws Exception {
        when(usuarioService.obtenerUsuarios()).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/usuarios/listar"))
                .andExpect(status().isOk());
    }

    @Test
    void crearRol_ok() throws Exception {
        Rol rol = new Rol(null, "ADMIN", "ALL");
        when(rolRepository.save(any(Rol.class))).thenReturn(rol);

        mockMvc.perform(post("/api/v1/usuarios/crearRol")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rol)))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarPermisosRol_ok() throws Exception {
        Rol rol = new Rol(1L, "ADMIN", "NEW");
        when(usuarioService.actualizarPermisosRol(Mockito.eq(1L), any(String.class))).thenReturn(rol);

        mockMvc.perform(put("/api/v1/usuarios/permisos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("NEW")))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerRoles_ok() throws Exception {
        when(usuarioService.obtenerRoles()).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/usuarios/roles"))
                .andExpect(status().isOk());
    }
}