package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import model.Usuarios;
import org.springframework.web.bind.annotation.*;
import service.UsuariosService;

@RestController
@RequestMapping("/api/usuarios") 
public class UsuariosController {
    @Autowired
   private UsuariosService usuariosService; 

   // Obtiene todos los Usuarios
   @GetMapping
   public List<Usuarios> obtenerTodosLosUsuarios() {
    System.out.println("Solicitando todos los usuarios");
    return usuariosService.obtenerTodosLosUsuarios();
   }

   // obtiene un usuario por Id
   @GetMapping("/{id}")
   public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Long id) {
    Optional<Usuarios> usuarios = usuariosService.obtenerUsuariosPorId(id);
    return usuarios.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   // Crear un nuevo usuarios
   @PostMapping
   public ResponseEntity<Usuarios> crearUsuario(@RequestBody Usuarios usuarios) {
    Usuarios nuevoUsuarios = usuariosService.guardarUsuario(usuarios);
    return new ResponseEntity<>(nuevoUsuarios, HttpStatus.CREATED);
   }

   // Actualizar un usuario que ya existe
   @PutMapping("/{id}")
   public ResponseEntity<Usuarios> actualizarUsuarios(@PathVariable Long id, @RequestBody Usuarios usuariosDetalles) {
    Optional<Usuarios> usuariosExistente = usuariosService.obtenerUsuariosPorId(id);
    if (usuariosExistente.isPresent()) {
        Usuarios usuarios = usuariosExistente.get();
        usuarios.setNombre(usuariosDetalles.getNombre());
        usuarios.setEmail(usuariosDetalles.getEmail());
        usuarios.setPassword(usuariosDetalles.getPassword());
        usuarios.setRol(usuariosDetalles.getRol());

        Usuarios usuariosActualizado = usuariosService.guardarUsuario(usuarios);
        return ResponseEntity.ok(usuariosActualizado);
    } 
    else {
        return ResponseEntity.notFound().build();
    }

   }

   // Eliminar un usuario por Id
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
    usuariosService.eliminarUsuario(id);
    return ResponseEntity.noContent().build();
   }

 

}
