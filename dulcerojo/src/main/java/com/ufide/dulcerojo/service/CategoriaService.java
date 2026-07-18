package com.ufide.dulcerojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufide.dulcerojo.entity.Categoria;
import com.ufide.dulcerojo.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Categoria guardar(Categoria categoria) {
        return repo.save(categoria);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}