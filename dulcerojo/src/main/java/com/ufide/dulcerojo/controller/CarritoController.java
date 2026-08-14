package com.ufide.dulcerojo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufide.dulcerojo.entity.DetallePedido;
import com.ufide.dulcerojo.entity.Pedido;
import com.ufide.dulcerojo.entity.Producto;
import com.ufide.dulcerojo.entity.Usuario;
import com.ufide.dulcerojo.repository.UsuarioRepository;
import com.ufide.dulcerojo.service.PedidoService;
import com.ufide.dulcerojo.service.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<DetallePedido> carrito = obtenerCarrito(session);
        BigDecimal total = carrito.stream()
                .map(d -> d.getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        return "carrito";
    }

    @PostMapping("/agregar/{id}")
    public String agregar(@PathVariable Long id,
            @RequestParam(defaultValue = "1") int cantidad,
            HttpSession session,
            RedirectAttributes ra) {
        Producto producto = productoService.buscarPorId(id).orElse(null);
        if (producto == null) {
            ra.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/productos";
        }

        List<DetallePedido> carrito = obtenerCarrito(session);
        boolean existe = false;
        for (DetallePedido d : carrito) {
            if (d.getProducto().getId().equals(id)) {
                d.setCantidad(d.getCantidad() + cantidad);
                existe = true;
                break;
            }
        }
        if (!existe) {
            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(BigDecimal.valueOf(producto.getPrecio()));
            carrito.add(detalle);
        }

        session.setAttribute("carrito", carrito);
        ra.addFlashAttribute("ok", "Producto agregado al carrito");
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{index}")
    public String eliminar(@PathVariable int index, HttpSession session) {
        List<DetallePedido> carrito = obtenerCarrito(session);
        if (index >= 0 && index < carrito.size()) {
            carrito.remove(index);
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/confirmar")
    public String confirmar(HttpSession session,
            Authentication auth,
            RedirectAttributes ra) {
        List<DetallePedido> carrito = obtenerCarrito(session);
        if (carrito.isEmpty()) {
            ra.addFlashAttribute("error", "El carrito está vacío");
            return "redirect:/carrito";
        }

        Usuario usuario = usuarioRepo.findByCorreo(auth.getName()).orElse(null);
        if (usuario == null) {
            return "redirect:/login";
        }

        BigDecimal total = carrito.stream()
                .map(d -> d.getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setTotal(total);
        pedido.setUsuario(usuario);
        pedidoService.guardar(pedido);

        for (DetallePedido d : carrito) {
            d.setPedido(pedido);
            pedidoService.guardarDetalle(d);

            // Bajar el stock del producto
            Producto p = d.getProducto();
            p.setStock(p.getStock() - d.getCantidad());
            productoService.guardar(p);
        }

        session.removeAttribute("carrito");
        ra.addFlashAttribute("ok", "¡Pedido confirmado! Tu pedido está en camino.");
        return "redirect:/productos";
    }

    @SuppressWarnings("unchecked")
    private List<DetallePedido> obtenerCarrito(HttpSession session) {
        List<DetallePedido> carrito = (List<DetallePedido>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }
}
