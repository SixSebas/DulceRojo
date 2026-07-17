package com.ufide.dulcerojo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ufide.dulcerojo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByNombreContainingIgnoreCase(String texto);
}