package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Usuarios;
import repository.UsuariosRepository;

@Service
public class UsuariosService {
     @Autowired
    private UsuariosRepository usuariosRepository;

    // Obtener todos los usuarios
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    // Buscar usuario por Id
    public Optional<Usuarios> obtenerUsuariosPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    // Buscar usuario por email
    public Optional<Usuarios> obtenerUsuarioPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    // Guardar o actualizar un usuario
    public Usuarios guardarUsuario(Usuarios usuarios) {
        return usuariosRepository.save(usuarios);
    }

    // Eliminar un usuario por Id
    public void eliminarUsuario(Long id) {
        usuariosRepository.deleteById(id);
    }
}
