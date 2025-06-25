package com.AdminSystem.AdminSystem.Controller;

import org.springframework.web.bind.annotation.*;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Service.UsuarioService;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios y roles")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public UsuarioController(UsuarioService usuarioService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("{id}")
    public Map<String, String> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return Map.of("mensaje", "Usuario eliminado exitosamente");
    }

    @Operation(summary = "Obtener la lista de todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Usuario.class)))
    @GetMapping("/listar")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol creado exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/crearRol")
    public Rol crearRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    @Operation(summary = "Actualizar permisos de un rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Permisos actualizados exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/permisos/{idRol}")
    public Rol actualizarPermisosRol(@PathVariable Long idRol, @RequestBody String permisos) {
        return usuarioService.actualizarPermisosRol(idRol, permisos);
    }

    @Operation(summary = "Obtener la lista de roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Rol.class)))
    @GetMapping("/roles")
    public List<Rol> obtenerRoles() {
        return usuarioService.obtenerRoles();
    }
}