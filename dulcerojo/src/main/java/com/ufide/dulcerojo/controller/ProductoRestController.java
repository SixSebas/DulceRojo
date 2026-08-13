package com.ufide.dulcerojo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import com.ufide.dulcerojo.entity.Producto;
import com.ufide.dulcerojo.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService service;

    // GET /api/productos -> lista todos los productos en JSON
    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    // GET /api/productos/{id} -> 200 OK o 404
    @GetMapping("/{id}")
    public ResponseEntity<Producto> detalle(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/productos -> 201 Created
    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody Producto producto) {
        Producto guardado = service.guardar(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();
        return ResponseEntity.created(location).body(guardado);
    }

    // PUT /api/productos/{id} -> 200 OK o 404
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id,
            @Valid @RequestBody Producto producto) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        producto.setId(id);
        return ResponseEntity.ok(service.guardar(producto));
    }

    // DELETE /api/productos/{id} -> 204 No Content o 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
