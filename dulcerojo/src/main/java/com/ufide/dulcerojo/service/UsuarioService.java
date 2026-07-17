package com.ufide.dulcerojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufide.dulcerojo.entity.Usuario;
import com.ufide.dulcerojo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setRol("USER");
        return repo.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return repo.findByCorreo(correo).isPresent();
    }
}
