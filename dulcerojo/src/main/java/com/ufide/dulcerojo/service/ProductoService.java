package com.ufide.dulcerojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufide.dulcerojo.entity.Producto;
import com.ufide.dulcerojo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repo.findByCategoriaNombre(categoria);
    }

    public List<Producto> buscarPorNombre(String texto) {
        return repo.findByNombreContainingIgnoreCase(texto);
    }
}