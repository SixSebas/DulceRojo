package com.ufide.dulcerojo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ufide.dulcerojo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);
}