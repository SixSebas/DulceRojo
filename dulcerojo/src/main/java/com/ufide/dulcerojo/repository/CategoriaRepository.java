package com.ufide.dulcerojo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufide.dulcerojo.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

}
