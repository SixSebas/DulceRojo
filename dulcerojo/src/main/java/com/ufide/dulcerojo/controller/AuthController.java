package com.ufide.dulcerojo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.dulcerojo.entity.Usuario;
import com.ufide.dulcerojo.service.UsuarioService;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "auth/registro";
        }
        if (usuarioService.existeCorreo(usuario.getCorreo())) {
            result.rejectValue("correo", "error.usuario", "Este correo ya está registrado");
            return "auth/registro";
        }
        usuarioService.registrar(usuario);
        ra.addFlashAttribute("ok", "Registro exitoso, iniciá sesión");
        return "redirect:/login";
    }
}
