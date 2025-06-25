package com.AdminSystem.AdminSystem.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.AdminSystem.AdminSystem.Model.Rol;
import com.AdminSystem.AdminSystem.Model.Usuario;
import com.AdminSystem.AdminSystem.Repository.RolRepository;
import com.AdminSystem.AdminSystem.Service.UsuarioService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios y roles")
@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public UsuarioControllerV2(UsuarioService usuarioService, RolRepository rolRepository) {
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
    public EntityModel<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.crearUsuario(usuario);
        return EntityModel.of(creado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).crearUsuario(usuario)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerUsuarios()).withRel("usuarios")
        );
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return EntityModel.of(actualizado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).actualizarUsuario(id, usuario)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerUsuarios()).withRel("usuarios")
        );
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
    public CollectionModel<EntityModel<Usuario>> obtenerUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.obtenerUsuarios().stream()
            .map(usuario -> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).actualizarUsuario(usuario.getId(), usuario)).withRel("actualizar"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).eliminarUsuario(usuario.getId())).withRel("eliminar")
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerUsuarios()).withSelfRel()
        );
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol creado exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/crearRol")
    public EntityModel<Rol> crearRol(@RequestBody Rol rol) {
        Rol creado = rolRepository.save(rol);
        return EntityModel.of(creado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).crearRol(rol)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerRoles()).withRel("roles")
        );
    }

    @Operation(summary = "Actualizar permisos de un rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Permisos actualizados exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/permisos/{idRol}")
    public EntityModel<Rol> actualizarPermisosRol(@PathVariable Long idRol, @RequestBody String permisos) {
        Rol actualizado = usuarioService.actualizarPermisosRol(idRol, permisos);
        return EntityModel.of(actualizado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).actualizarPermisosRol(idRol, permisos)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerRoles()).withRel("roles")
        );
    }

    @Operation(summary = "Obtener la lista de roles")
    @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Rol.class)))
    @GetMapping("/roles")
    public CollectionModel<EntityModel<Rol>> obtenerRoles() {
        List<EntityModel<Rol>> roles = usuarioService.obtenerRoles().stream()
            .map(rol -> EntityModel.of(rol,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).actualizarPermisosRol(rol.getId(), rol.getPermisos())).withRel("actualizarPermisos")
            ))
            .collect(Collectors.toList());
        return CollectionModel.of(roles,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioControllerV2.class).obtenerRoles()).withSelfRel()
        );
    }
}