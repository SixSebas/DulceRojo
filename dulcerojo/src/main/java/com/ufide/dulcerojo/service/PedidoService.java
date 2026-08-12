package com.ufide.dulcerojo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufide.dulcerojo.entity.Pedido;
import com.ufide.dulcerojo.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public List<Pedido> listar() {
        return repo.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return repo.save(pedido);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}