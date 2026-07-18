package com.ufide.dulcerojo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.dulcerojo.entity.Producto;
import com.ufide.dulcerojo.service.CategoriaService;
import com.ufide.dulcerojo.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", service.listar());
        return "productos";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute(
                "producto",
                service.buscarPorId(id).orElse(null));

        return "producto";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listar());

        return "productos/form";
    }

    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult result,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "productos/form";
        }

        service.guardar(producto);
        ra.addFlashAttribute("ok", "Producto guardado correctamente");

        return "redirect:/productos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {

        Producto producto = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listar());

        return "productos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult result,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "productos/form";
        }

        producto.setId(id);
        service.guardar(producto);

        ra.addFlashAttribute("ok", "Producto actualizado correctamente");

        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes ra) {

        service.eliminar(id);

        ra.addFlashAttribute("ok", "Producto eliminado correctamente");

        return "redirect:/productos";
    }
}